package together.pet.domain.facilities.dto;

import java.sql.Date;

import lombok.Data;

/**
 * 리뷰 DTO
 */
@Data
public class Review {
	private int reviewNum ;
	private String reviewInfo;
	private Date reviewDate ;
	private int	reviewGrade ;
	private String 	id ;
	private int	facNum ;
}
