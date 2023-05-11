package together.pet.domain.member.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import together.pet.domain.board.dto.Post;
import together.pet.domain.board.dto.Reply;
import together.pet.domain.facilities.dto.Review;
import together.pet.domain.member.dto.Favorite;
import together.pet.domain.member.dto.Member;
import together.pet.domain.member.dto.myReserve;

/**
 * 해당 경로에 회원 관련 기능을 추상적으로 적습니다 메소드 이름, 리턴타입, 전달인자에 맞춰 XML 파일에서 구현합니다
 */
@Mapper
public interface MemberMapper {
	// 로그인
	public Member login(String id);

	// 회원가입
	public void join(Member member);

	// 아이디 중복체크
	public String idcheck(String id);

	// 회원수정
	public void update(Member member);

	// 회원탈퇴
	public void delete(@Param("id") String id, @Param("password") String password);

	// 즐겨찾기
	public List<Favorite> bookmark(String id);

	// 내 게시글 보기
	public List<Post> seePost(String id);

	// 내 리뷰 보기
	public List<Review> seeReview(String id);

	// 내 댓글 보기
	public List<Reply> seeComment(String id);

	// 아이디 찾기
	public String findId(@Param("name") String name, @Param("email") String email);

	// 비밀번호 찾기
	public String findPassword(@Param("id") String id, @Param("name") String name, @Param("email") String email);

	// 비밀번호 변경
	public void updatePw(@Param("id") String id, @Param("password") String password);

// 예약 목록 전부 (완)
	public List<myReserve> findReserveAll(String id);

//예약 수정 (마이페이지로)
	public void editReserve(@Param("id") String id, @Param("facNum") int facNum, @Param("resDate") String resDate,
			@Param("resPeoples") int resPeoples);

//예약 취소 (마이페이지로)
	public void deleteReserve(@Param("id") String id, @Param("facNum") int facNum);

}
