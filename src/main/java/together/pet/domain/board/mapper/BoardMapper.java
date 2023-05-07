package together.pet.domain.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import together.pet.domain.board.dto.Post;
import together.pet.domain.board.dto.PostSearch;
import together.pet.domain.board.dto.Reply;
import together.pet.domain.board.dto.Report;
import together.pet.web.common.RequestParams;

/**
 * 해당 경로에 게시판 관련 기능을 추상적으로 적은뒤,
 * 메소드 이름, 리턴타입, 전달인자에 맞춰 XML 파일에서 구현합니다
 * @author 유선우
 */
@Mapper
@Transactional
public interface BoardMapper {
	//게시글 작성
	public void write(Post post);
	
	/**
	 * @param params
	 * 페이징 정보를 담아 전달합니다.
	 * 경우에 따라, 검색값을 저장할수도 있습니다
	 * params에 검색값을 입력하고, 검색 결과를 반환합니다. 기본 리스트 반환과 동일하게 params의 
	 * 페이징 값 또한 이용합니다. 	
	 * private int requestPage : 초기에 보여줄 페이지를 의미합니다    
	 * private int elementSize : 한 페이지에 보여줄 게시글의 갯수를 설정합니다
	 * private int pageSize : 페이징 처리시, 하단 번호를 보여줄 갯수를 설정합니다 설정된 갯수가 넘어가면
	 * 다움페이지로 이동하거나, 이전페이지로 이동하는 버튼이 생성되도록 도와줍니다
	 * @return List<Post>
	 * 게시글 리스트를 반환합니다
	 */
	public List<Post> getPostAll(RequestParams params);
	
	/**
	 * @param params
	 * 페이징 정보를 담아 전달합니다.
	 * 해당 메소드는 검색쿼리문을 함께 이용하여, 검색값이 있는경우, 없는경우를 고려하여
	 * 총 열 갯수를 반환합니다
	 * @return int getPostCount
	 */
	public int getPostCount(RequestParams params);
	
	
	/**
	 * @param postNum
	 * 게시글 번호를 통해 해당 게시글의 상세정보를 반환합니다
	 * @return Post
	 */
	public Post postDetail(int postNum);
	
	
	
	/**
	 * @param reply
	 * 댓글 정보를 전달받고, DB에 입력합니다.
	 */
	public void writeReply(Reply reply);
	
	
	
	/**
	 * @param postNUm
	 * 게시글 번호를 통해 해당 게시글 번호로 입력되어있는 댓글들을 반환받습니다.
	 * @return List<Reply>
	 */
	public List<Reply> getReplyAll(int postNUm);
	
	
	/**
	 * @param postNum
	 * 게시글 번호로 게시글을 삭제합니다
	 */
	public void deletePost(int postNum);
	
	
	/**
	 * @param commentNum
	 * 댓글 번호로 댓글을 삭제합니다
	 */
	public void deleteReply(int commentNum);
	
	
	
	/**
	 * @param reportNum
	 * 신고 번호로 신고를 삭제합니다
	 */
	public void deleteReport(int reportNum);
	
	
	/**
	 * @param postNum
	 * 특정 게시글의 상세 정보를 불러올때, 해당 메소드를 이용하여 조회수를 증가시킵니다
	 */
	public void addViews(int postNum);
	
	
	/**
	 * @param post
	 * 게시글 정보를 전달받고, 전달받은 게시글 번호, 내용, 제목, 타입을 통해 게시글을 수정합니다.
	 * 작성자 아이디, 혹은 날짜는 수정이 불가능합니다
	 */
	public void updatePost(Post post);
	
	
	/**
	 * @param reply
	 * 댓글 정보를 전달받고, 전달받은 댓글번호, 내용을 통해 댓글을 수정합니다.
	 * 작성자 아이디, 날짜, 게시글 번호 등은 수정이 불가능합니다
	 */
	public void updateReply(Reply reply);
	
	
	/**
	 * @param params
	 * params에 검색값을 입력하고, 검색 결과를 반환합니다. 기본 리스트 반환과 동일하게 params의 
	 * 페이징 값 또한 이용합니다.
	 * params 필드 설명  	
	 * private int requestPage : 초기에 보여줄 페이지를 의미합니다    
	 * private int elementSize : 한 페이지에 보여줄 게시글의 갯수를 설정합니다
	 * private int pageSize : 페이징 처리시, 하단 번호를 보여줄 갯수를 설정합니다 설정된 갯수가 넘어가면
	 * 다움페이지로 이동하거나, 이전페이지로 이동하는 버튼이 생성되도록 도와줍니다
	 * private String day : 검색하는 날짜가 일인지, 월인지 분류하는 역할입니다
	 * private String date : 해당 필드가 존재할경우, ~일 단위로 검색을 실행합니다. 
	 * private String month : 해당 필드가 존재할경우 ~월 단위로 검색을 실행합니다.
	 * private String type : 검색 타입에 따라 search 값으로 검색을 실행합니다
	 * private String search : 검색되는 문자열을 저장합니다
	 * @return
	 */
	public List<Post> searchPost(RequestParams params);
	
	
	/**
	 * @param report
	 * 신고 데이터를 전달받고 신고를 진행합니다
	 */
	public void report(Report report);
	
	
	/**
	 * @return
	 * 댓글 신고 내용을 과 댓글 내용을 모두 가져옵니다
	 */
	public List<Report> getPostReport();
	
	
	
	/**
	 * @return
	 * 게시글 신고 내용과 게시글을 모두 가져옵니다
	 */
	public List<Report> getReplyReport();
	
	
	/**
	 * @return
	 * 최신 게시글 5개를 가져옵니다
	 */
	public List<Post> recentPost();
	
	
	/**
	 * @return
	 * 조회수가 가장 많은 게시글 5개를 가져옵니다
	 */
	public List<Post> popularityPost();
	
	
	/**
	 * @return
	 * 최신 공지사항 목록 3개를 가져옵니다
	 */
	public List<Post> notice();
}
