package together.pet.domain.board.service;

import java.util.List;

import together.pet.domain.board.dto.Post;
import together.pet.domain.board.dto.PostSearch;
import together.pet.domain.board.dto.Reply;
import together.pet.domain.board.dto.Report;
import together.pet.web.common.RequestParams;

/**
 * 컨트롤러에서 사용할 서비스 클래스입니다.
 * 우선 추상적으로 Mapper클래스의 기능 사용을 정의하고,
 * 이후 해당 인터페이스를 상속받아 구현하는 서비스 클래스를 통해, 컨트롤러에서 사용하게됩니다
 * @author 유선우
 */
public interface BoardService {
	//게시글 추가
	public void writePost(Post post);
	//상세정보
	public Post postDetail(int postNum);
	//게시글댓글작성
	public void writeReply(Reply reply);
	//댓글리스트
	public List<Reply> getReplyAll(int postNum);
	//게시글 삭제
	public void deletePost(int pustNum);
	//댓글 삭제
	public void deleteReply(int commentNum);
	//신고 삭제
	public void deleteReport(int reportNum);
	//조회수 추가
	public void addViews(int pustNum);
	//게시글 수정
	public void updatePost(Post post);
	//댓글 수정
	public void updateReply(Reply reply);
	//게시글 받기
	public List<Post> getPostAll(RequestParams params);
	//게시글 검색
	public List<Post> SearchPost(RequestParams params);
	//카운트 받기
	public int getPostCount(RequestParams params);
	//게시글or댓글 신고
	public void report(Report report);
	//게시글 신고 받아오기
	public List<Report> getPostReport();
	//댓글 신고 받아오기
	public List<Report> getReplyReport();
	//최신 게시글 받아오기
	public List<Post> recentPost();
	//인기글 받아오기
	public List<Post> popularityPost();
	//공지사항 받아오기
	public List<Post> notice();

}
