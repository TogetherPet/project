package together.pet.domain.board.dto;

import java.util.Date;

import lombok.Data;

/**
 *게시글 DTO
 */
@Data
public class Post {
	//게시글 번호
	private int postNum;
	//페이지 번호 가져옴
	private int page;
	//게시글 제목
	private String postTitle;
	//게시글 내용
	private String postContent;
	//게시글 타입
	private String postType;
	//아이디
	private String id;
	//작성날짜
	private String postDate;
	//조회수
	private int postViews;
	//추천수
	private int postGreat;
}
