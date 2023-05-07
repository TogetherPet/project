package together.pet.domain.board.dto;

import lombok.Data;

@Data
public class PostSearch {
	//검색날짜가 date인지, month인지 구분하는 역할입니다
	private String day;
	// ~일 검색일 경우 사용합니다
	private String date;
	// ~달 검색일 경우 사용합니다
	private String month;
	//검색 타입을 결정합니다
	private String type;
	//검색값을 저장합니다
	private String search;
}
