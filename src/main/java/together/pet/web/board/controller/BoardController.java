package together.pet.web.board.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonObject;

import oracle.net.aso.r;
import together.pet.domain.board.dto.Post;
import together.pet.domain.board.dto.PostSearch;
import together.pet.domain.board.dto.Reply;
import together.pet.domain.board.dto.Report;
import together.pet.domain.board.service.BoardService;
import together.pet.domain.member.dto.Member;
import together.pet.web.common.Pagination;
import together.pet.web.common.RequestParams;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	BoardService boardService;

	// 페이징에서 사용하는 변수입니다.
	// 처음 페이지
	private int requestPage = 1;
	// 한 페이지에 보여줄 수
	private int elementSize = 5;
	// 하단 번호 보여줄 수
	private int pageSize = 4;

	/**
	 * @return 아무것도 입력하지 않고 /board로 접근할경우, 첫 페이지로 이동합니다
	 */
	@GetMapping
	public String first() {
		return "redirect:/board/1";
	}

	
	@GetMapping("/{page}")
	public String list(Model model, @PathVariable String page) {
		/**
		 * 페이지를 선택한다면 입력할정보입니다. 아무페이지도 입력하지 않을경우, 위에서 설정한 초기 페이지로 이동합니다
		 */
		if (page != null && !page.equals("")) {
			requestPage = Integer.parseInt(page);
		}

		/**
		 * 찾아올 설정정보를 담은 params 객체를 생성합니다. 해당 객체를 이용하여, 총 페이지 갯수와 해당 페이지의 게시글을 가져옵니다
		 */
		RequestParams params = new RequestParams(requestPage, elementSize, pageSize, null);
		int selectCount = boardService.getPostCount(params);
		/**
		 * 페이지 설정 정보와 검색된 게시글의 갯수를 통해, 페이징 설정을 진행합니다
		 */
		Pagination pagination = new Pagination(params, selectCount);
		List<Post> list = boardService.getPostAll(params);
		System.out.println(pagination);

		/**
		 * 공지사항을 가져와 슬라이드에서 이용합니다
		 */
		List<Post> notice = boardService.notice();
		/**
		 * 검색정보들을 저장하고, 이 정보를 통해 게시글 번호, 페이징처리의 링크를 지정하게됩니다
		 */
		model.addAttribute("Params", params);
		/**
		 * 리스트를 저장합니다
		 */
		model.addAttribute("PostList", list);
		/**
		 * 페이징 정보를 전달합니다. 해당 객체의 메소드를통해 다음페이지 버튼 출력 여부를 결정합니다
		 */
		model.addAttribute("Pagination", pagination);
		/**
		 * 공지사항을 전달합니다
		 */
		model.addAttribute("notice", notice);
		
		return "board/CommunityList";
	}

	
	@GetMapping("/detail/{postNum}")
	public String postdetail(@PathVariable int postNum, Model model) {
		/**
		 * 상세정보로 조회하는경우, 해당 게시글 번호로 DB에 접근하여 조회수를 1 증가시킵니다
		 */
		boardService.addViews(postNum);
		
		/**
		 * 공지사항을 가져와 슬라이드에서 이용합니다
		 */
		List<Post> notice = boardService.notice();
		model.addAttribute("notice", notice);
		
		/**
		 * 게시글 번호를 통해 상세정보를 가져옵니다
		 */
		Post post = boardService.postDetail(postNum);
		model.addAttribute("postDetail", post);

		/**
		 * 게시글 번호에 해당하는 댓글을 가져옵니다
		 */
		List<Reply> replyList = boardService.getReplyAll(postNum);
		model.addAttribute("postReply", replyList);

		return "board/PostDetail";
	}

	/**
	 * @param session
	 * @param request
	 * @param model
	 * @return sessoin의 아이디를 가져오고, 저장된 아이디가 없다면 로그인 후에 이용 가능하다는 에러메세지를 입력하고, 해당
	 *         스크립트를 출력한뒤 이전 페이지로 돌려보내는 html파일로 이동시킵니다. 저장된 아이디가 있다면 게시글 입력이 가능한 폼으로
	 *         이동시킵니다.
	 */
	@GetMapping("/write")
	public String write(HttpSession session, HttpServletRequest request, Model model) {
		/**
		 * 저장된 아이디가 없다면 해당 if문이 실행됩니다
		 */
		if (session.getAttribute("member") == null) {
			/**
			 * request.getHeader을 통해 헤더 정보를 가져오고, 해당 헤더정보와 함께 에러메세지를 담아 에러페이지로 이동시킵니다
			 */
			model.addAttribute("err", "로그인 후에 이용할수 있는 기능입니다");
			model.addAttribute("referer", request.getHeader("Referer"));
			return "member/loginerr";
		}
		/**
		 * 저장된 아이디가 있다면 게시글 입력 폼으로 이동합니다
		 */
		return "board/WritePost";
	}

	/**
	 * @param post
	 * @param Session
	 * @return WritePost.html에서 Post정보를 입력받아 게시글을 작성합니다. 빈 객체와 이름을
	 *         매칭시켜 @ModelAttribute 를 통해 객체를 생성했습니다. 아이디 정보는 세션에서 가져와 입력합니다
	 */
	@PostMapping("/write")
	public String writePost(@ModelAttribute Post post, HttpSession Session) {
		/**
		 * 저장된 Member객체의 아이디를 가져와 게시글 작성 아이디를 입력한뒤, 게시글을 작성하고 게시판의 맨 처음 페이지로 redirect
		 * 시킵니다
		 */
		Member member = (Member) Session.getAttribute("member");
		post.setId(member.getId());
		boardService.writePost(post);
		return "redirect:/board";
	}

	/**
	 * @param reply
	 * @param request
	 * @param session
	 * @return PostDetail.html에서 게시글 작성 폼을 통해 댓글 내용을 입력받습니다. 아이디는 세션에서 가져와 저장합니다.
	 */
	@PostMapping("/writeReply")
	public String writeReply(@ModelAttribute Reply reply, HttpServletRequest request, HttpSession session) {
		Member member = (Member) session.getAttribute("member");
		reply.setId(member.getId());
		boardService.writeReply(reply);
		/**
		 * 댓글을 작성한뒤 다시 이전페이지로 이동합니다
		 */
		return "redirect:" + request.getHeader("Referer");

	}

	/**
	 * @param postNum
	 * @return
	 * Delete 요청으로 게시글 번호가 입력되면, 해당 게시글을 삭제하고,
	 * 게시글 메인페이지로 이동합니다
	 */
	@DeleteMapping("/delet/post/{postNum}")
	public String deletPost(@PathVariable int postNum, HttpServletRequest request, HttpSession session, Model model) {
		
		
		boardService.deletePost(postNum);
		/**
		 * 관리자 페이지에서 접근한경우, 이전 경로로 이동합니다
		 */
		if(request.getHeader("referer").equals("http://localhost/board/report/view")) {
			return "redirect:" + request.getHeader("referer");
		}
		/**
		 * 다른 페이지에서 접근한경우, 게시글이 삭제되었으므로, 게시판으로 이동시킵니다
		 */
		return "redirect:/board";
	}
	
	/**
	 * @param commnetNum
	 * @param request
	 * @return
	 * Delete 요청으로 댓글 번호가 입력되면, 해당 댓글을 삭제하고,
	 * 게시글 상세 이동합니다
	 */
	@DeleteMapping("/delet/reply/{commnetNum}")
	public String deletPosst(@PathVariable int commnetNum, HttpServletRequest request) {
		boardService.deleteReply(commnetNum);
		return "redirect:" + request.getHeader("referer");
	}
	
	/**
	 * @param repostNum
	 * @param request
	 * @return
	 */
	@DeleteMapping("/delet/report/{reportNum}")
	public String deletReport(@PathVariable int reportNum, HttpServletRequest request) {
		boardService.deleteReport(reportNum);
		return "redirect:" + request.getHeader("referer");
	}

	/**
	 * @param postNum
	 * @param session
	 * @param request
	 * @param model
	 * @return
	 * 해당 게시글 번호로 업데이트 요청이 들어오면, 해당 게시글 번호를 저장하고 
	 * writePost.html 로 이동합니다
	 * 로그인되어있지 않다면, 에러페이지로 이동합니다
	 */
	@GetMapping("/update/getpost/{postNum}")
	public String getUpdatePost(@PathVariable int postNum, HttpSession session, HttpServletRequest request,
			Model model) {
		/**
		 * 세션의 Member객체를 가져오고, 저장된 데이터가 없다면 에러페이지로 이동합니다
		 */
		Member member = (Member) session.getAttribute("member");
		if (member == null) {
			model.addAttribute("err", "로그인 후 이용 가능합니다");
			model.addAttribute("referer", request.getHeader("referer"));
			return "member/loginerr";
		}
		/**
		 * 정상적으로 진행되면 해당 게시글 번호로 정보를 받아오고, 게시글 작성 페이지로 이동합니다
		 */
		Post post = boardService.postDetail(postNum);
		model.addAttribute("postDetail", post);
		return "board/writePost";
	}

	/**
	 * @param page
	 * @param params
	 * @param model
	 * @param request
	 * @return
	 * 검색을 진행하고,해당 검색값에 맞춰 페이징 처리를 진행합니다
	 * 해당 페이지 번호 ? 파라메터값으로 검색을 진행합니다
	 */
	@GetMapping("/post/search/{page}")
	public String searchPost(@ModelAttribute RequestParams params, @PathVariable String page, Model model,
			HttpServletRequest request) {
		
		if (page != null && !page.equals("")) {
			requestPage = Integer.parseInt(page);
		}
		/**
		 * 파람 객체를 전달받기때문에 추가적으로 set 메소드를 호출하여
		 * 페이징 데이터를 입력합니다
		 */
		params.setRequestPage(requestPage);
		params.setElementSize(elementSize);
		params.setPageSize(pageSize);
		
		/**
		 * 해당 파람 데이터를 통해 검색된 정보와 페이지 갯수를 전달받습니다
		 */
		List<Post> list = null;
		int selectCount = 0;
		if(params.getSearch().equals("인기글")) {
			//정렬 다시하기
			list = boardService.getPostAll(params);
			params.setType(null);
			selectCount = boardService.getPostCount(params);
			params.setType("postType");
		}else {
			//머릿글 검색등
			list = boardService.SearchPost(params);
			selectCount = boardService.getPostCount(params);
		}
		/**
		 * 파람 데이터와 검색 갯수를 통해 페이징 데이터를 전달받습니다
		 */
		Pagination pagination = new Pagination(params, selectCount);
		
		/**
		 * 검색 타입을 저장하고, 웹페이지에섣 무슨 검색을 진행했는지 활용합니다
		 */
		if (params.getType().equals("postType")) {
			model.addAttribute("postType", params.getSearch());
		}else if(params.getType().equals("id")){
			model.addAttribute("postType", params.getSearch()+"님의 게시글");
		}
		
		/**
		 * 공지사항을 가져와 슬라이드에서 이용합니다
		 */
		List<Post> notice = boardService.notice();
		model.addAttribute("notice", notice);

		model.addAttribute("Params", params);
		model.addAttribute("PostList", list);
		model.addAttribute("Pagination", pagination);
		
		return "board/CommunityList";
	}

	/**
	 * @param post
	 * @param postNum
	 * @param model
	 * @return
	 * 게시글수정 메소드입니다.
	 * 기본적인 게시글 구조와, 게시글 번호를 받고 수정을 진행합니다
	 */
	@PostMapping("/update/post/{postNum}")
	public String updatePost(@ModelAttribute Post post, @PathVariable("postNum") int postNum, Model model) {
		post.setPostNum(postNum);
		boardService.updatePost(post);
		// 수정한 경로로 보냄
		return "redirect:/board/detail/" + postNum;
	}

	/**
	 * @param reply
	 * @param replyNum
	 * @param request
	 * @return
	 * 댓글 수정 메소드입니다.
	 * 기본적인 댓글 구조와, 댓글 번호를 받고 수정을 진행합니다
	 */
	@PostMapping("/update/reply/{replyNum}")
	public String updateRepley(@ModelAttribute Reply reply, @PathVariable("replyNum") int replyNum,
			HttpServletRequest request) {
		reply.setCommentNum(replyNum);
		boardService.updateReply(reply);
		return "redirect:" + request.getHeader("referer");
	}
	
	/**
	 * @param report
	 * @param num
	 * @param type
	 * @param request
	 * @param session
	 * @param model
	 * @return
	 * 신고 기능입니다.
	 * html에서 보낸 type에 따라, 댓글, 게시글에 데이터를 저장하고,
	 * 데이터가 들어간 쪽의 신고가 진행됩니다.
	 */
	@PostMapping("/report")
	public String report(@ModelAttribute Report report,@RequestParam int num,@RequestParam String type,HttpServletRequest request, HttpSession session,Model model) {
		Member member = (Member)session.getAttribute("member");
		model.addAttribute("referer", request.getHeader("referer"));
		/**
		 * 로그인 되어있지 않을경우, 기능을 이용할수 없도록 했습니다
		 */
		if(member == null) {
			model.addAttribute("err", "신고 기능은 로그인 후에 이용할 수 있습니다");
			return "member/loginerr";
		}
		/**
		 * message를 통해 사용자가 어느 신고를 실행했는지 출력합니다
		 * post 인 경우, 게시글 신고가,
		 * reply 인 경우, 댓글 신고가 실행됩니다
		 */
		String message = null;
		if(type.equals("post")) {
			report.setPostNum(num);
			message = "게시글 신고가";
		}else if(type.equals("reply")){
			report.setCommentNum(num);
			message = "댓글 신고가";
		}else {
			/**
			 * 일치하는 타입이 없을경우, 에러가 출력됩니다
			 */
			model.addAttribute("err", "게시글 혹은 댓글을 확인해주세요");
			return "member/loginerr";
		}
		
		/**
		 * report객체에 아이디를 지정하고,
		 * 신고를 접수한후 사용자에게 정상적으로 진행되었다는 메세지를 출력합니다
		 */
		report.setReportId(member.getId());
			boardService.report(report);
			model.addAttribute("err", message+" 정상적으로 접수되었습니다");
		return "member/loginerr";
	}
	
	/**
	 * @param session
	 * @param model
	 * @param request
	 * @return
	 * 관리자 페이지 접근 컨트롤러입니다.
	 * 사용자 아이디 타입을 검사하여, 관리자가 아니거나 비 로그인 상태인경우 오류창을 출력시킵니다
	 */
	@GetMapping("/report/view")
	public String reportView(HttpSession session, Model model, HttpServletRequest request) {
		/**
		 * 아이디 검사를 진행하여, 세션 아이디의 타입이 관리자가 아닐경우,혹은 아이디가 없을경우, 접근할수 없도록 합니다
		 */
		Member member = (Member)session.getAttribute("member");
		if(member == null || !member.getAdmin().equals("관리자")) {
			model.addAttribute("err", "관리자만 접근할수 있는 페이지입니다");
			model.addAttribute("referer", request.getHeader("referer"));
			return "member/loginerr";
		}else if(member.getAdmin().equals("관리자")) {
			//관리자이면 데이터를 저장하고 이동시킵니다
			List<Report> post = boardService.getPostReport();
			List<Report> reply =  boardService.getReplyReport();
			model.addAttribute("post", post);
			model.addAttribute("reply", reply);
		}
		return "board/report";
	}

}
