package together.pet.domain.board.service;

import java.util.List;

import org.springframework.aop.target.HotSwappableTargetSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import together.pet.domain.board.dto.Post;
import together.pet.domain.board.dto.PostSearch;
import together.pet.domain.board.dto.Reply;
import together.pet.domain.board.dto.Report;
import together.pet.domain.board.mapper.BoardMapper;
import together.pet.web.common.RequestParams;

@Service
@Transactional
public class BoardServiceImpl implements BoardService {
	@Autowired
	BoardMapper boardMapper;
	
	@Override
	public void writePost(Post post) {
		boardMapper.write(post);
	}

	@Override
	public List<Post> getPostAll(RequestParams params) {
		List<Post> list = boardMapper.getPostAll(params);
		return list;
	}

	@Override
	public Post postDetail(int postNum) {
		Post post = boardMapper.postDetail(postNum);
		return post;
	}

	@Override
	public void writeReply(Reply reply) {
		boardMapper.writeReply(reply);
	}

	@Override
	public List<Reply> getReplyAll(int postNum) {
		List<Reply> list = boardMapper.getReplyAll(postNum);
		return list;
	}

	@Override
	public void deletePost(int pustNum) {
		boardMapper.deletePost(pustNum);
		
	}

	@Override
	public void deleteReply(int commentNum) {
		boardMapper.deleteReply(commentNum);
		
	}

	@Override
	public void addViews(int pustNum) {
		boardMapper.addViews(pustNum);
	}

	@Override
	public void updatePost(Post post) {
		boardMapper.updatePost(post);
	}

	@Override
	public List<Post> SearchPost(RequestParams params) {
		
		/**
		 *Day에 저장된 값에따라 검색을 실행합니다
		 */
		if(params.getDay().equals("day/7")) {
			/**
			 *Day가 day/7인 경우, Date 객체에 검색값을 저장합니다
			 */
			params.setDate(params.getDay().substring(4));
		}
		
		else if(params.getDay().substring(0, 5).equals("month")) {
			/**
			 *Day가 month인 경우, month 객체에 검색값을 저장합니다
			 */
			params.setMonth(params.getDay().substring(6));
		}
		
		List<Post> list = boardMapper.searchPost(params);
		//검색결과를 반환받고, 컨트롤러에 전달합니다
		return list;
	}

	@Override
	public int getPostCount(RequestParams params) {
		return boardMapper.getPostCount(params);
	}
	
	@Override
	public void updateReply(Reply reply) {
		boardMapper.updateReply(reply);
		
	}
	
	@Override
	public void report(Report report) {
		boardMapper.report(report);
	}

	@Override
	public void deleteReport(int reportNum) {
		boardMapper.deleteReport(reportNum);
	}

	@Override
	public List<Report> getPostReport() {
		return boardMapper.getPostReport();
	}

	@Override
	public List<Report> getReplyReport() {
		return boardMapper.getReplyReport();
	}
	
	@Override
	public List<Post> recentPost() {
		return boardMapper.recentPost();
	}
	
	@Override
	public List<Post> popularityPost() {
		return boardMapper.popularityPost();
	}
	
	@Override
	public List<Post> notice() {
		return boardMapper.notice();
	}
}
