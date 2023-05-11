package together.pet.domain.member.service;

import java.util.List;

import org.springframework.stereotype.Service;

import together.pet.domain.board.dto.Post;
import together.pet.domain.board.dto.Reply;
import together.pet.domain.facilities.dto.Review;
import together.pet.domain.member.dto.Favorite;
import together.pet.domain.member.dto.Member;
import together.pet.domain.member.dto.myReserve;

/**
 * 컨트롤러에서 사용할 서비스 클래스입니다. 우선 추상적으로 Mapper클래스의 기능 사용을 정의하고, 이후 해당 인터페이스를 상속받아
 * 구현하는 서비스 클래스를 통해, 컨트롤러에서 사용하게됩니다
 * 
 * @author 유선우
 */

public interface MemberService {

	// 로그인
	public Member login(String id);

	// 회원가입
	public void join(Member member);

	// 아이디 중복체크
	public String idcheck(String id);

	// 회원수정
	public void update(Member member);

	// 회원탈퇴
	public void delete(String id, String password);

	// 즐겨찾기
	public List<Favorite> bookmark(String id);

	// 내 게시글 보기
	public List<Post> seePost(String id);

	// 내 리뷰 보기
	public List<Review> seeReview(String id);

	// 내 댓글 보기
	public List<Reply> seeComment(String id);

	// 아이디 찾기
	public String findId(String name, String email);

	// 비밀번호 찾기
	public String findPassword(String id, String name, String email);

	// 비밀번호 변경
	public void updatePw(String name, String password);

// 예약정보 (나)
	public List<myReserve> findReserveAll(String id);

	// 예약 수정 (마이페이지로)
	public void editReserve(String id, int facNum, String resDate, int resPeoples);

	// 예약 취소
	public void deleteReserve(String id, int facNum);

}
