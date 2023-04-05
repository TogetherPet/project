package together.pet.domain.member.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import together.pet.domain.board.dto.Post;
import together.pet.domain.board.dto.Reply;
import together.pet.domain.member.dto.Favorite;
import together.pet.domain.member.dto.Member;

/**
 *해당 경로에 회원 관련 기능을 추상적으로 적습니다
 *메소드 이름, 리턴타입, 전달인자에 맞춰 XML 파일에서 구현합니다
 */
@Mapper
public interface MemberMapper {
	//회원가입
	public void join(Member member);
	
	//로그인
	public void login(@Param("id") String id, @Param("password") String password);
	
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
