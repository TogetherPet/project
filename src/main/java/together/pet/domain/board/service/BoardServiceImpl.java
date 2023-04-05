package together.pet.domain.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import together.pet.domain.board.dto.Post;
import together.pet.domain.board.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	BoardMapper boardMapper;
	
	@Override
	public void writePost(Post post) {
		boardMapper.write(post);
	}

	@Override
	public List<Post> getPostAll() {
		List<Post> list = boardMapper.getPostAll();
		return list;
	}

	@Override
	public Post postDetail(int postNum) {
		Post post = boardMapper.postDetail(postNum);
		return post;
	}

}
