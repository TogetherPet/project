package together.pet.domain.board.dto;

import lombok.Data;

/**
 * type, title, id, date, content, postNum, reportNum
 * 신고 DTO
 */
@Data
public class Report {
	//신고 번호
	private int reportNum;
	//신고 사유
	private String reportReason;
	//신고자 아이디
	private String reportId;
	//신고 날짜
	private String reportDate;
	
	
	//댓글번호
	private int commentNum;
	//댓글내용
	private String commentContent;
	//댓글작성날짜
	private String commentDate;
	
	//게시글 번호
	private int postNum;
	//게시글 제목
	private String postTitle;
	//게시글 내용
	private String postContent;
	//게시글 타입
	private String postType;
	//작성날짜
	private String postDate;
	
	//아이디
	private String id;
	
}
