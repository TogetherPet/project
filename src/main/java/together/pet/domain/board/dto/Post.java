package together.pet.domain.board.dto;

import java.util.Date;

import lombok.Data;

/**
 *게시글 DTO
 */
@Data
public class Post {
	private int postNum;
	private String postTitle;
	private String postContent;
	private String postType;
	private String id;
	private String postDate;
	private int postViews;
	private int postGreat;
}
