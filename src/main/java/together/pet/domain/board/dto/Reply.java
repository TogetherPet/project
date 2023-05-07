package together.pet.domain.board.dto;

import java.util.Date;

import lombok.Data;

/**
 * 댓글 DTO
 */
@Data
public class Reply {
	//댓글번호
	private int commentNum;
	//댓글내용
	private String commentContent;
	//작성날짜
	private String commentDate;
	//댓글이 작성된 게시글 번호
	private int postNum;
	//작성자 아이디
	private String id;
}
