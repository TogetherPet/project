package together.pet.domain.facilities.dto;

import java.sql.Date;

import lombok.Data;

/**
 * 예약 DTO
 */
@Data
public class Reserve {
	private int resNum  ;
	private String resDate ;
	private int resPeoples ;
	private String id ;
	private int facNum ;
}
