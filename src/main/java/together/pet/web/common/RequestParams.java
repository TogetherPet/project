package together.pet.web.common;

import lombok.Data;

/**
 * 여러개의 요청파라메터들을 포장하기 위한 자바빈
 */
@Data
public class RequestParams {
	private int requestPage;    /** 사용자 요청 페이지 */
	private int elementSize;    /** 페이지에 보여지는 목록 개수 */
	private int pageSize;       /** 페이지에 보여지는 페이지 개수 */
	private String day; 		/** 검색 요일 */
	private String date;		/** 검색 일 */
	private String month;		/** 검색 월 */
	private String type;		/** 검색 타입 */
	private String search;		/** 검색 정보 */
	
	//시설에서 사용할 필드
	private String city;
	private String kind;
	private String county;

	public RequestParams() {
		this(1, 10, 5, null);
	}
	
	public RequestParams(int requestPage, int elementSize, int pageSize, String search) {
		this.requestPage = requestPage;
		this.elementSize = elementSize;
		this.pageSize = pageSize;
		this.search = search;
	}

}
