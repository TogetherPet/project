package together.pet.domain.facilities.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import together.pet.domain.facilities.dto.Facilities;
import together.pet.domain.facilities.dto.Reserve;
import together.pet.domain.facilities.dto.Review;
import together.pet.domain.member.dto.Favorite;
import together.pet.web.common.RequestParams;

/**
 * 컨트롤러에서 사용할 서비스 클래스입니다. 우선 추상적으로 Mapper클래스의 기능 사용을 정의하고, 이후 해당 인터페이스를 상속받아
 * 구현하는 서비스 클래스를 통해, 컨트롤러에서 사용하게됩니다
 *
 */
public interface FacilitiesService {
//	public List<Facilities> findAll();

	public Facilities findDetail(@PathVariable("num") int num);

	public Facilities findByNum(int num);

	public Facilities getFacilityByRetail(String facName, String addRoad);

//	public List<Facilities> findByCity(String facCity, String facCounty, String facCatagory, String search);

//	public List<Facilities> findByCityhospital(String facCity, String facCounty, String facCatagory, String search);

	// 예약
	public List<Reserve> getAllReserves();


	public void deleteReserve(Reserve reserve);

	public void editReserve(Reserve reserve);

	// 예약 추가 (완)
	public void addReserve(Reserve reserve);
	// 예약 목록
	public List<Reserve> reserveList(@Param("id") String id, @Param("facNum") int facNum);

	// 리뷰
	// 마이 페이지 리뷰 목록
	public List<Review> mypageReviewall(String id);

	// 상세 페이지 리뷰 목록
	public List<Review> findByReviewall(int facNum);

	public void addReview(Review review);

	public void deleteReview(String id);

	public void editReview(Review review);

	// 병원 리스트출력
//	public List<Facilities> findHospitalall();

	// 병원 상세페이지 내용 출력
	public Facilities findHospitalDetail(@PathVariable("num") int num);



	// 즐겨찾기 등록
	public void addFavorite(String id, int facNum);

	// 즐찾 아이디로 찾기(등록 여부 확인)
	public List<Favorite> findFavoriteById(String id, int facNum);

	// 즐찾 삭제
	public void deleteFavorite(String id, int facNum);

    /////페이징 처리///////
    

    //페이지 수
    public int getListCount();
    
    //페이지 처리
    public List<Facilities> getListPage(@Param("num") int num) ;

    public List<Facilities> getListHospital(@Param("num") int num) ;

    
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
    
    //병원 4종류 가져옴
    public List<Facilities> hospitalSlide();
    
}
