package together.pet.domain.board.service;

import java.util.List;

import together.pet.domain.board.dto.Post;

/**
 * 컨트롤러에서 사용할 서비스 클래스입니다.
 * 우선 추상적으로 Mapper클래스의 기능 사용을 정의하고,
 * 이후 해당 인터페이스를 상속받아 구현하는 서비스 클래스를 통해, 컨트롤러에서 사용하게됩니다
 * @author 유선우
 */
public interface BoardService {
	public void writePost(Post post);
	
	public List<Post> getPostAll();
	
	public Post postDetail(int postNum);
}
