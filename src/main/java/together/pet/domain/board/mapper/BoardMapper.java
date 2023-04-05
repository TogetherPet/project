package together.pet.domain.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import together.pet.domain.board.dto.Post;

/**
 * 해당 경로에 게시판 관련 기능을 추상적으로 적은뒤,
 * 메소드 이름, 리턴타입, 전달인자에 맞춰 XML 파일에서 구현합니다
 * @author 유선우
 */
@Mapper
public interface BoardMapper {
	public void write(Post post);
	
	public List<Post> getPostAll();
	
	public Post postDetail(int postNum);
	
}
