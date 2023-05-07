package together.pet.domain.facilities.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import together.pet.domain.facilities.dto.Facilities;
import together.pet.domain.facilities.dto.Reserve;
import together.pet.domain.facilities.dto.Review;
import together.pet.domain.facilities.mapper.FacilitiesMapper;
import together.pet.domain.member.dto.Favorite;
import together.pet.web.common.RequestParams;

@Service
@Transactional(readOnly = true) 
public class FacilitiesServiceImpl implements FacilitiesService {
	
	@Autowired
	FacilitiesMapper facilitiesMapper;
	

	
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
	    //모달창 값 받아서 리뷰 추가
		@Override
		public void addReview(Review review) {
			facilitiesMapper.addReview(review);
			
		}

		//리뷰 삭제
		@Transactional 
		@Override
		public void deleteReview(String id) {
		    facilitiesMapper.deleteReview(id);
		}

		//리뷰 수정
		@Override
		public void editReview(Review review) {
		    facilitiesMapper.editReview(review);
		}
		
	
		
		@Override
		//병원 상세페이지 내용 출력
		public Facilities findHospitalDetail(@PathVariable("num") int num) {
			return facilitiesMapper.findHospitalDetail(num);
		};
		
	
	
		// 예약목록 리스트
		@Override
		public List<Reserve> reserveList(String id, int facNum) {
			return facilitiesMapper.reserveList(id, facNum);
		}

		

		// 즐겨찾기 등록
		@Override
		public void addFavorite(String id, int facNum) {
			facilitiesMapper.addFavorite(id, facNum);
		}
		// 즐찾 아이디로 찾기(등록 여부 확인)
		public List<Favorite> findFavoriteById(String id, int facNum) {
			return facilitiesMapper.findFavoriteById(id, facNum);
		}
		// 즐찾 삭제
		@Override
		public void deleteFavorite(String id, int facNum) {
			facilitiesMapper.deleteFavorite(id, facNum);
			
		}
		
		
		

		@Override
		public int getListCount() {
		
			return facilitiesMapper.getListCount();
		}

		@Override
		public int getListCount2(RequestParams requestParams) {
	
			return facilitiesMapper.getListCount2(requestParams);
		}
		
		@Override
		public int getListCountHospital(){
			return facilitiesMapper.getListCountHospital();
		}
		
		
		@Override
		public int getListCountHospital2(RequestParams requestParams){
			return facilitiesMapper.getListCountHospital2(requestParams);
		}
		
		
		@Override
	    //페이지 처리
	    public List<Facilities> getListPage(int num){
	    	return facilitiesMapper.getListPage(num);
	    
	    }
		
		
		@Override
		public List<Facilities> findByCityHospital2(RequestParams requestparams) {
			return facilitiesMapper.findByCityHospital2(requestparams);
		}
		
		@Override	
		public List<Facilities> getListHospital(int num) {
			return facilitiesMapper.getListPageHospital(num);
		}
		
		@Override
		public List<Facilities> findByCity2(RequestParams requestparams) {
		
			return facilitiesMapper.findByCity2(requestparams);
		}

		@Override
		public List<Facilities> hospitalSlide() {
			return facilitiesMapper.hospitalSlide();
		}
		
}
