package together.pet.web.member.controller;


import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import together.pet.domain.member.dto.Member;
import together.pet.domain.member.service.MemberService;

@Controller
@RequestMapping("/pet")
public class MemberController {
	
	@Autowired
	private MemberService memberService;

	@GetMapping
	public String main() {
		return "member/main";
	}

	@GetMapping("/join")
	public String movejoin() {
		return "member/join";
	}

	@PostMapping("/join")
	public void join(@ModelAttribute Member member, HttpServletResponse response) {
		//받아온 날짜를 여기서 종합합니다
		member.setBirth(member.getYear()+member.getMonth()+member.getDate());
		//회원등급은 임시로 일반회원으로 설정
		member.setAdmin("일반회원");
		
		memberService.join(member);
			try {
				response.sendRedirect("/pet");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
}
