package together.pet.domain.facilities.dto;

import java.sql.Date;

import lombok.Data;

/**
 * 리뷰 DTO
 */
@Data
public class Review {
	private int review_num ;
	private String review_info;
	private Date review_date ;
	private int	review_grade ;
	private String 	id ;
	private int	fac_num ;
	
}
