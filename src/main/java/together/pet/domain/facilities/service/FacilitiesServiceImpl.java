package together.pet.domain.facilities.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import together.pet.domain.facilities.dto.Facilities;
import together.pet.domain.facilities.dto.Reserve;
import together.pet.domain.facilities.dto.Review;
import together.pet.domain.facilities.mapper.FacilitiesMapper;

@Service
public class FacilitiesServiceImpl implements FacilitiesService {
	
	@Autowired
	FacilitiesMapper facilitiesMapper;
	
	@Override
	public List<Facilities> findAll() {
		return facilitiesMapper.findAll();
	}
	
	@Override
	public Facilities findDetail(@PathVariable("num") int num) {
		return facilitiesMapper.findDetail(num);
	}
	
	@Override
	public Facilities findByNum(int num) {
		return facilitiesMapper.findDetail(num);
	}
	
	//상세 페이지 아래에 세부 사항 목록 출력
		@Override
		public Facilities getFacilityByRetail(String facName, String addRoad) {
		    return facilitiesMapper.findByRetail(facName, addRoad);
		}

		
		//이름으로 찾기
		@Override
		public Facilities findFacilityByName(String facName) {
		    return facilitiesMapper.findByName(facName);
		}

		//지역으로 찾기
		@Override
		public Facilities getFacilityByCity(String facCity, String facCounty, String facCatagory, String search) {
		    return facilitiesMapper.findByCity(facCity, facCounty, facCatagory, search);
		}

		//통합 검색 
		@Override
		public List<Facilities> getFacilitiesBySearchAll() {
		    return facilitiesMapper.findBySearchAll();
		}

		// 예약 목록 전부 
		@Override
		public List<Reserve> getAllReserves() {
		    return facilitiesMapper.findByReserveall();
		}

		// 예약 목록 추가  
		@Override
		public void addReserve(Reserve reserve) {
		    facilitiesMapper.addReserve(reserve);
		}

		//예약 목록 삭제 
		@Override
		public void deleteReserve(Reserve reserve) {
		    facilitiesMapper.deleteReserve(reserve);
		}

		//예약 수정
		@Override
		public void editReserve(Reserve reserve) {
		    facilitiesMapper.editReserve(reserve);
		}

		//상세 페이지 리뷰 목록  
		@Override
		public List<Review> findByReviewall(int facNum) {
		    return facilitiesMapper.findByReviewall(facNum);
		}

		
	    //마이 페이지 리뷰 목록
	    public List<Review> mypageReviewall(String id){
	    	return facilitiesMapper.mypageReviewall(id);
	    }

		//리뷰 추가
		@Override
		public void addReview(String id) {
		    facilitiesMapper.addReview(id);
		}

		//리뷰 삭제
		@Override
		public void deleteReview(String id) {
		    facilitiesMapper.deleteReview(id);
		}

		//리뷰 수정
		@Override
		public void editReview(String id) {
		    facilitiesMapper.editReview(id);
		}
}
