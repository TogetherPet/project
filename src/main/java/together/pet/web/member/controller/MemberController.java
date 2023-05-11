package together.pet.web.member.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import together.pet.domain.board.dto.Post;
import together.pet.domain.board.dto.Reply;
import together.pet.domain.board.service.BoardService;
import together.pet.domain.facilities.dto.Facilities;
import together.pet.domain.facilities.dto.Review;
import together.pet.domain.facilities.service.FacilitiesService;
import together.pet.domain.member.dto.Favorite;
import together.pet.domain.member.dto.Member;
import together.pet.domain.member.dto.myReserve;
import together.pet.domain.member.service.MemberService;
import together.pet.web.common.Pagination;
import together.pet.web.common.RequestParams;

@Controller
@RequestMapping("/pet")
@Slf4j
public class MemberController {

	@Autowired
	private MemberService memberService;

	@Autowired
	BoardService boardService;

	@Autowired
	FacilitiesService facilitiesService;

	// 아이디 저장
	@GetMapping
	public String main(@CookieValue(value = "save-id", required = false) String saveId, Model model) {
		model.addAttribute("saveId", saveId);
		List<Post> recnet = boardService.recentPost();
		List<Post> popular = boardService.popularityPost();
		List<Facilities> hospital = facilitiesService.hospitalSlide();
		model.addAttribute("recnet", recnet);
		model.addAttribute("popular", popular);
		model.addAttribute("hospital", hospital);
		return "member/main";
	}

	// 로그인 처리
	@PostMapping("/login")
	public String login(@RequestParam String id, @RequestParam String password, HttpSession session, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		Member member = null;
		String err = null;
		model.addAttribute("referer", request.getHeader("referer"));
		member = memberService.login(id);

		if (member == null) {
			err = "아이디가 존재하지 않습니다";
			model.addAttribute("err", err);
		} else {
			if (member.getPassword().equals(password)) {
				session.setAttribute("member", member);
				if (null != request.getParameter("remember-check")) {
					Cookie cookie = new Cookie("save-id", id);
					cookie.setMaxAge(60 * 60 * 24 * 30); // 30일간 유효
					cookie.setPath("/"); // 쿠키 사용 경로
					response.addCookie(cookie);
				}
				String referer = request.getHeader("Referer");
				return "redirect:" + referer;

			} else {
				err = "비밀번호가 일치하지 않습니다";
				model.addAttribute("err", err);
			}
		}
		return "redirect:" + request.getHeader("Referer");
	}

	// 로그아웃
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate(); // 세션 무효화
		}
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@GetMapping("/join")
	public String movejoin() {
		return "member/join";
	}

	// 마이페이지
	@GetMapping("/mypage")
	public String mypage(HttpSession session, Model model) {
		Member member = (Member) session.getAttribute("member");
		List<myReserve> list = memberService.findReserveAll(member.getId());
		model.addAttribute("list", list);

		model.addAttribute("good", "bad");
		return "member/mypage";
	}

	// 회원아이디 중복체크
	      @PostMapping("/idcheck")
	      @ResponseBody
	      public boolean idCheck(@RequestBody String id) {
	         String real = id.substring(3);
	         System.out.println(real);
	         System.out.println(memberService.idcheck(real));
	         if(memberService.idcheck(real).equals("ok")) {
	            System.out.println(memberService.idcheck(real));
	            return false;
	         }else {
	            return true;
	         }
	      }

	// 회원가입
	@PostMapping("/join")
	public void join(@ModelAttribute Member member, HttpServletResponse response) {
		// 받아온 날짜를 여기서 종합합니다
		member.setBirth(member.getYear() + member.getMonth() + member.getDate());
		// 회원등급은 임시로 일반회원으로 설정
		member.setAdmin("일반회원");

		memberService.join(member);
		try {
			response.sendRedirect("/pet");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 회원 정보 수정 전 확인 창
	@RequestMapping(value = "/mypageEdit", method = RequestMethod.POST)
	@ResponseBody
	public String mypageEdit(@RequestParam String id, @RequestParam String pw, HttpSession session,
			HttpServletRequest request, Model model) {
		log.info("수정 아이디 : {}", id);
		log.info("수정 비전 : {}", pw);
		String referer = request.getHeader("referer");
		Member member = (Member) session.getAttribute("member");
		if (member.getPassword().equals(pw)) {
			session.setAttribute("member", member);
			model.addAttribute("referer", referer);
			model.addAttribute("good", "good");
			return "true";
		} else {
			model.addAttribute("err", "err");
			model.addAttribute("good", "bad");
			return "false";
		}
	}

	// 회원 정보 수정 처리
	@PostMapping("/mypageUpdate")
	public String mypageUpdate(@ModelAttribute Member member, HttpSession session) {

		if (member.getId().equals(member.getId())) {
			memberService.update(member);
			System.out.println("테스트 : " + member);
			Member newMember = memberService.login(member.getId());
			session.setAttribute("member", newMember);
			return "redirect:/pet/mypage";
		} else {
			return "redirect:/pet/mypage";
		}
	}

	// 회원 탈퇴
	@DeleteMapping("/mypageDelete")
	public String delete(@RequestParam String id, @RequestParam String password, HttpSession session, Model model,
			HttpServletRequest request) {
		String referer = request.getHeader("referer");
		Member member = memberService.login(id);
		String err = null;
		if (member.getPassword().equals(password)) {
			session.setAttribute("member", member);
			memberService.delete(id, password);
			session.invalidate(); // 세션 무효화
			return "redirect:/pet";
		} else {
			err = "비밀번호가 일치하지 않습니다";
			model.addAttribute("err", err);
			model.addAttribute("referer", referer);
			return "member/loginerr";
		}
	}

	// 내 게시글 보기
	@GetMapping("/myPost")
	@ResponseBody
	public List<Post> seepost(HttpSession session, Model model) {
		Member member = (Member) session.getAttribute("member");
		String id = member.getId();
		List<Post> post = memberService.seePost(id);
		model.addAttribute("post", post);
		return post;
	}

	// 내 댓글 보기
	@GetMapping("/myComment")
	@ResponseBody
	public List<Reply> seeComment(HttpSession session, Model model) {
		Member member = (Member) session.getAttribute("member");
		String id = member.getId();
		List<Reply> comment = memberService.seeComment(id);
		model.addAttribute("comment", comment);
		return comment;
	}

	// 내 리뷰 보기
	@GetMapping("/myReview")
	@ResponseBody
	public List<Review> seeReview(HttpSession session, Model model) {
		Member member = (Member) session.getAttribute("member");
		String id = member.getId();
		List<Review> review = memberService.seeReview(id);
		model.addAttribute("review", review);
		return review;
	}

	// 즐겨찾기
	@GetMapping("/myfavorite")
	@ResponseBody
	public List<Favorite> bookmark(HttpSession session, Model model) {
		Member member = (Member) session.getAttribute("member");
		String id = member.getId();
		List<Favorite> favorite = memberService.bookmark(id);
		model.addAttribute("review", favorite);
		return favorite;
	}

	// 아이디 찾기
	@GetMapping("/findId")
	public String findId() {
		return "/member/findId";
	}

	@PostMapping("/findIdByName")
	public String findIdByName(Model model, HttpServletRequest request) {
		String name = request.getParameter("member_name");
		System.out.println(name);
		String email = request.getParameter("email");
		System.out.println(email);
		String id = memberService.findId(name, email);
		System.out.println(id);
		if (id == null) {
			String err = "입력하신 회원정보가 존재하지 않습니다.";
			model.addAttribute("err", err);
			model.addAttribute("referer", request.getHeader("referer"));
			return "/member/loginerr";
		} else {
			model.addAttribute("id", id);
			return "/member/findId";
		}
	}

	// 비밀번호 찾기
	@GetMapping("/findPassword")
	public String findPassword() {
		return "/member/findPassword";
	}

	@PostMapping("/findPasswordByName")
	public String findPasswordByName(HttpServletRequest request, Model model) {
		String id = request.getParameter("member_id");
		String name = request.getParameter("member_name");
		String email = request.getParameter("email");

		String pw = memberService.findPassword(id, name, email);
		if (pw == null) {
			String err = "입력하신 회원정보가 존재하지 않습니다.";
			model.addAttribute("err", err);
			model.addAttribute("referer", request.getHeader("referer"));
			return "/member/loginerr";
		} else {
			model.addAttribute("id", id);
			model.addAttribute("pw", pw);
			return "/member/findPassword";
		}
	}

	// 비밀번호 변경
	@PostMapping("/updatePw")
	public String updatePw(@RequestParam("id") String id, @RequestParam("password") String password,
			@RequestParam("password2") String password2) {
		if (password.equals(password2)) {
			memberService.updatePw(id, password);
		}
		return "member/main";
	}

	@GetMapping("/myreserve")
	@ResponseBody
	public List<myReserve> findReserveAll(HttpSession session, Model model) {
		Member member = (Member) session.getAttribute("member");
		String id = member.getId();

//		log.info("내 아이디 : {}", id);

		List<myReserve> reserveList = memberService.findReserveAll(id);
		// List<myReserve> reserveList = memberService.findReserveAll("test");
		for (myReserve list : reserveList) {
			log.info("내 예약정보 : {}", list);
			model.addAttribute("list", list);
		}
		return reserveList;
	}

	@GetMapping("/edit/reserve")
	public String editReserve(@RequestParam("facNum") int facNum, @RequestParam("daterange") String daterange,
			@RequestParam("count") int count, HttpSession session, Model model, HttpServletRequest request) {
		Member member = (Member) session.getAttribute("member");
		String id = member.getId();
		log.info("정보 : {}", facNum, daterange, count);

		memberService.editReserve(id, facNum, daterange, count);

		model.addAttribute("success", "success");

		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@GetMapping("/delete/reserve")
	public String deleteReserve(@RequestParam("id") String id, @RequestParam("facNum") int facNum, Model model,
			HttpServletRequest request) {
		log.info("아이디 : {}", id);
		log.info("시설번호 : {}", facNum);

		memberService.deleteReserve(id, facNum);

		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

//	// 페이징 처리
//	@GetMapping
//	public String a(HttpServletRequest request) {
//		HttpSession session = request.getSession();
//		Member member = (Member)session.getAttribute("member");
//		System.out.println(member);
//		// 로그인 중이면
//		if(member != null) {
//			// 페이징 처리와 관련된 변수
//			int requestPage = 1;
//			int elementSize = 5;
//			int pageSize = 5;
//			String search = null;
//			
//			String selectPage = request.getParameter("page");
//			System.out.println("1. " + selectPage);
//			if (selectPage != null && !selectPage.equals("")) {
//				requestPage = Integer.parseInt(selectPage);
//			}
//			System.out.println("2. " + requestPage);
//			search = request.getParameter("search");
//			if(search != null && search.equals("")) {
//				selectPage = null;
//			}
//			System.out.println("3. " + selectPage);
//			
//			// 여러개의 요청 파라메터 정보 저장
//			RequestParams params = new RequestParams(requestPage, elementSize, pageSize, search);
//			
////			List<Member> list2 = memberService.getMembers();
//			List<Member> list = memberService.getMembers(params);
//			
//			
//			int selectCount = memberService.getMemberCount(params);
//			Pagination pagination = new Pagination(params, selectCount);
//			
//			Map<String, Object>  page = new HashMap<>();
//			page.put("params", params);         // 요청 파라메터
//			page.put("list", list);             // 검색 목록
//			page.put("pagination", pagination); // 페이징 계산 결과
//			request.setAttribute("page", page);
//			
//			return "/pet/mypage";
//		}

//	}
}

class User {
	   private final String id;
	    User(String id) {
	      this.id = id;
	   }
	    
	    public String getId() {
	       return id;
	    }
	}
