package together.pet.domain.facilities.dto;

import java.sql.Date;

import lombok.Data;

/**
 * 예약 DTO
 */
@Data
public class Reserve {
	private int res_num  ;
	private Date res_date ;
	private int res_peoples ;
	private String id ;
	private int fac_num ;
}
