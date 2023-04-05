package together.pet.web.board.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import together.pet.domain.board.dto.Post;
import together.pet.domain.board.service.BoardService;
import together.pet.domain.member.dto.Member;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	@GetMapping
	public String list(Model model) {
		List<Post> list =  boardService.getPostAll();
		model.addAttribute("PostList", list);
		return "board/CommunityList";
	}
	
	@GetMapping("/detail/{postNum}")
	public String postdetail(@PathVariable int postNum , Model model) {
		Post post = boardService.postDetail(postNum);
		model.addAttribute("postDetail", post);
		return "board/PostDetail";
	}
	
	@GetMapping("/write")
	public String write() {
		return "board/WritePost";
	}
	
	@PostMapping("/write")
	public String writePost(@ModelAttribute Post post, HttpSession Session) {
		Member member = (Member) Session.getAttribute("member");
		post.setId(member.getId());
		System.out.println(post);
		boardService.writePost(post);
		
		return "board/CommunityList";
	}
}
