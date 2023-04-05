package together.pet.domain.member.service;

import java.util.List;

import org.springframework.stereotype.Service;

import together.pet.domain.board.dto.Post;
import together.pet.domain.board.dto.Reply;
import together.pet.domain.member.dto.Favorite;
import together.pet.domain.member.dto.Member;

/**
 * 컨트롤러에서 사용할 서비스 클래스입니다.
 * 우선 추상적으로 Mapper클래스의 기능 사용을 정의하고,
 * 이후 해당 인터페이스를 상속받아 구현하는 서비스 클래스를 통해, 컨트롤러에서 사용하게됩니다
 * @author 유선우
 */

public interface MemberService {
	
		//로그인
	   public void login(String id, String password);

	   // 회원가입
	   public void join(Member member);

	   // 회원수정
	   public void update(Member member);

	   // 회원탈퇴
	   public void delete(Member member);

	   // 내 댓글 보기
	   public List<Reply> seeComment(String id);

	   // 내 게시글 보기
	   public List<Post> seePost(String id);

	   // 즐겨찾기
	   public List<Favorite> bookmark(String id);

}
