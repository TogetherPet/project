package together.pet.domain.facilities.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import together.pet.domain.facilities.dto.Facilities;
import together.pet.domain.facilities.dto.Reserve;
import together.pet.domain.facilities.dto.Review;

/**
 * 컨트롤러에서 사용할 서비스 클래스입니다.
 * 우선 추상적으로 Mapper클래스의 기능 사용을 정의하고,
 * 이후 해당 인터페이스를 상속받아 구현하는 서비스 클래스를 통해, 컨트롤러에서 사용하게됩니다
 *
 */
public interface FacilitiesService {
	public List<Facilities> findAll();
	
	public Facilities findDetail(@PathVariable("num") int num);
	
	public Facilities findByNum(int num);
	
	public Facilities getFacilityByRetail(String facName, String addRoad);
	
	public Facilities findFacilityByName(String facName);
	
	public Facilities getFacilityByCity(String facCity, String facCounty, String facCatagory, String search);
	
	public List<Facilities> getFacilitiesBySearchAll();
	
	public List<Reserve> getAllReserves();
	
	public void addReserve(Reserve reserve);
	
	public void deleteReserve(Reserve reserve);
	
	public void editReserve(Reserve reserve);
	
	 //마이 페이지 리뷰 목록
    public List<Review> mypageReviewall(String id);

    //상세 페이지 리뷰 목록
	public List<Review> findByReviewall (int facNum);
	
	public void addReview(String id);
	
	public void deleteReview(String id);
	
	public void editReview(String id);

}
