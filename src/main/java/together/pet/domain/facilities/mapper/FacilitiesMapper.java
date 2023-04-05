package together.pet.domain.facilities.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import together.pet.domain.facilities.dto.Facilities;
import together.pet.domain.facilities.dto.Reserve;
import together.pet.domain.facilities.dto.Review;

/**
 *해당 경로에 시설 관련 기능을 추상적으로 적습니다
 *메소드 이름, 리턴타입, 전달인자에 맞춰 XML 파일에서 구현합니다
 */
@Mapper
public interface FacilitiesMapper {
		//전체 시설 리스트 검색
		public List<Facilities> findAll();
	
		//상세 페이지에 시설 목록 뽑기(이미지 옆 칸)
		public Facilities findDetail(@PathVariable("num") int num);
		
		// 시설 번호로 시설 찾기
		public Facilities findByNum(int num);
		
		
		//상세 페이지 아래에 세부 사항 목록 출력
		public Facilities findByRetail(@Param("facName") String facName , @Param("addRoad")String addRoad );
		
		//이름으로 찾기
		public Facilities findByName(String facName);
		
		//지역으로 찾기																																		   //검색 값 받기
	    public Facilities findByCity( @Param("facCity") String facCity,  @Param("facCounty")String facCounty,  @Param("facCatagory") String facCatagory,  @Param("search") String search);
	    
	    //통합 검색 
	    public List<Facilities> findBySearchAll();
	    
	    ////////reserve////////////
	    
	    // 예약 목록 전부 
	    public List<Reserve> findByReserveall();
	    
	    // 예약 목록 추가  
	    public void addReserve(Reserve reserve);
	    
	    //예약 목록 삭제 
	    public void deleteReserve(Reserve reserve);
	    
	    //예약 수정
	    public void editReserve(Reserve reserve);
	    
	    
	    //////////review/////////////
	    
	  //마이 페이지 리뷰 목록
	    public List<Review> mypageReviewall(String id);
	    
	    
	    //상세 페이지 리뷰 목록  
	    public List<Review> findByReviewall(int facNum);
	    
	    //리뷰 추가
	    public void addReview(String id);
	    
	    //리뷰 삭제
	    public void deleteReview(String id);

	    //리뷰 수정
	    public void editReview(String id);


}
