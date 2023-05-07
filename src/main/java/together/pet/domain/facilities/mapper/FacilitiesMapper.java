package together.pet.domain.facilities.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import together.pet.domain.facilities.dto.Facilities;
import together.pet.domain.facilities.dto.Reserve;
import together.pet.domain.facilities.dto.Review;
import together.pet.domain.member.dto.Favorite;
import together.pet.web.common.RequestParams;

/**
 *해당 경로에 시설 관련 기능을 추상적으로 적습니다
 *메소드 이름, 리턴타입, 전달인자에 맞춰 XML 파일에서 구현합니다
 */
@Mapper
public interface FacilitiesMapper {
	/**
	 *해당 경로에 시설 관련 기능을 추상적으로 적습니다
	 *메소드 이름, 리턴타입, 전달인자에 맞춰 XML 파일에서 구현합니다
	 */
			//전체 시설 리스트 검색
			//public List<Facilities> findAll();
		
			//상세 페이지에 시설 목록 뽑기(이미지 옆 칸)
			public Facilities findDetail(@PathVariable("num") int num);
			
			// 시설 번호로 시설 찾기
			public Facilities findByNum(int num);
			
			
			//상세 페이지 아래에 세부 사항 목록 출력
			public Facilities findByRetail(@Param("facName") String facName , @Param("addRoad")String addRoad );
		
			//지역으로 찾기																																		   //검색 값 받기
		    //public List<Facilities> findByCity( @Param("facCity") String facCity,  @Param("facCounty")String facCounty,  @Param("facCatagory") String facCatagory,  @Param("search") String search);
		      
		    //병원 지역으로 찾기
			//지역으로 찾기																																		   //검색 값 받기
		    //public List<Facilities> findByCityhospital( @Param("facCity") String facCity,  @Param("facCounty")String facCounty,  @Param("facCatagory") String facCatagory,  @Param("search") String search);
		    
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
		    
		    public List<Reserve> reserveList(@Param("id") String id, @Param("facNum") int facNum);
		    
		    
		    //////////review/////////////
		    
		    //마이 페이지 리뷰 목록
		    public List<Review> mypageReviewall(String id);
		    
		    
		    //상세 페이지 리뷰 목록  
		    public List<Review> findByReviewall(int facNum);
		    
		    //리뷰 추가(모달 값 받아오기)
		    public void addReview(Review review);
		    
		    //리뷰 삭제
		    public void deleteReview(String id);

		
		    //리뷰 수정
		    public void editReview(Review review);
		    
		    // 즐겨찾기 등록 (불러오는건 멤버에서)
		    public void addFavorite(@Param("id") String id, @Param("facNum") int facNum);
		    // 즐찾 등록할때 되있는지 확인 위해 아이디로 찾기
		    public List<Favorite> findFavoriteById(@Param("id") String id, @Param("facNum") int facNum);
		    // 즐찾 삭제
		    public void deleteFavorite(@Param("id") String id, @Param("facNum") int facNum);
		    
		   //병원 전체 목록 출력 
		   // public List<Facilities> findHospitalall();
		    
		    //병원 상세 내역 보기
		    public Facilities findHospitalDetail(@PathVariable("num") int num);

		    /////페이징 처리///////
		    

		    //페이지 수
		    public int getListCount();
		    
		    //페이지 처리
		    public List<Facilities> getListPage(@Param("num") int num) ;

		    public List<Facilities> getListPageHospital(@Param("num") int num) ;

		    
		    //검색 옵션별 페이지 처리
		    //지역으로 찾기																																		   //검색 값 받기
			public List<Facilities> findByCityHospital2(RequestParams requestparams);
			    
		    
		    
		    //검색 옵션별 페이지 처리
			//지역으로 찾기																																		   //검색 값 받기
		    public List<Facilities> findByCity2(RequestParams requestparams);
		    
		    //검색 옵션 페이지 수
		    public int getListCount2(RequestParams requestParam);
		    
		    
		    public int getListCountHospital();
		    
		    //검색 옵션 페이지 수
		    public int getListCountHospital2(RequestParams requestParam);
		    
		    //병원 리스트 4개 뽑아옴
		    public List<Facilities> hospitalSlide();
		    


}
