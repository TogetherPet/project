package together.pet.domain.board.dto;

import java.util.Date;

import lombok.Data;

/**
 * 댓글 DTO
 */
@Data
public class Reply {
	private int comment_num;
	private String comment_content;
	private Date comment_date;
	private int post_num;
	private String id;
}
