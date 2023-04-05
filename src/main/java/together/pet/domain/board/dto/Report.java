package together.pet.domain.board.dto;

import lombok.Data;

/**
 * 신고 DTO
 */
@Data
public class Report {
	private int report_num;
	private String report_reason;
	private String report_id;
	private int post_num;
	private int comment_num;
}
