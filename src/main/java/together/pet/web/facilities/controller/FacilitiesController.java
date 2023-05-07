package together.pet.web.facilities.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;
import together.pet.domain.facilities.dto.Facilities;
import together.pet.domain.facilities.dto.Reserve;
import together.pet.domain.facilities.dto.Review;
import together.pet.domain.facilities.service.FacilitiesService;
import together.pet.domain.member.dto.Favorite;
import together.pet.domain.member.dto.Member;
import together.pet.domain.member.service.MemberService;
import together.pet.web.common.Pagination;
import together.pet.web.common.RequestParams;

@Controller
@RequestMapping("/facilities")
@Slf4j
public class FacilitiesController {
	
	   // 페이징에서 사용하는 변수입니다.
	   // 처음 페이지
	   private int requestPage = 1;
	   // 한 페이지에 보여줄 수
	   private int elementSize = 9;
	   // 하단 번호 보여줄 수
	   private int pageSize = 3;
	
	@Autowired
	FacilitiesService facilitiesService;
	
	@Autowired
	MemberService memberService;
	
	//Pagination pagination;
	
//	
//	@GetMapping("/list")
//	public String findAll(Model model) {
//		List<Facilities> facilityList = facilitiesService.findAll();
//		log.info("{}",facilityList);
//		model.addAttribute("facilityList", facilityList);
//		return "facilities/listFacilities";
//	}
	
//	@GetMapping("/list/all")
//	   public String findByCity(@RequestParam(value = "city", required = false) String city, 
//	                      @RequestParam(value = "county", required = false) String county, 
//	                      @RequestParam(value = "kind", required = false) String kind,
//	                      @RequestParam(value = "search", required = false) String search, 
//	                      Model model,
//	                      HttpServletRequest request) {
//	      request.getSession();
//	      
//	      
//	      List<Facilities> facilities = null;
//	      if(city != null || county != null || kind != null || search != null) {
//	         facilities = facilitiesService.findByCity(city, county, kind, search);
//	      }else {
//	         facilities = facilitiesService.findAll();
//	      }
//	      model.addAttribute("facilityList", facilities);
//	     // System.out.println(facilities);
//	      return "facilities/listFacilities";
//	   }
//	
	
//	@GetMapping("/list/all/hospital")
//	   public String findByCityhospital(@RequestParam(value = "city", required = false) String city, 
//	                      @RequestParam(value = "county", required = false) String county, 
//	                      @RequestParam(value = "kind", required = false) String kind,
//	                      @RequestParam(value = "search", required = false) String search, 
//	                      Model model,
//	                      HttpServletRequest request) {
//	      request.getSession();
//	      
//	      
//	      List<Facilities> facilities = null;
//	      if(city != null || county != null || kind != null || search != null) {
//	         facilities = facilitiesService.findByCityhospital(city, county, kind, search);
//	      }else {
//	         facilities = facilitiesService.findHospitalall();
//	      }
//	      model.addAttribute("hospitalList", facilities);
//	      System.out.println(facilities);
//	      return "facilities/list-hospital";
//	   }
	// 예약 등록
		@GetMapping("/reserve/{num}")
		public String addReserve(@RequestParam(value = "name2", required = false) String name, 
								 @RequestParam(value = "count", required = false) int count, 
								 @RequestParam(value = "daterange", required = false) String daterange,
								 @RequestParam(value = "num2", required = false) int num2,
								 @PathVariable("num") int num,
								 HttpServletRequest request,
								 HttpSession session, 
								 Model model) {
				
					Member member = (Member)session.getAttribute("member");
					List<Reserve> list = facilitiesService.reserveList(member.getId(), num);
					Reserve addReserve = new Reserve();
					addReserve.setFacNum(num);
					addReserve.setId(member.getId());
					addReserve.setResDate(daterange);
					addReserve.setResNum(num2);
					addReserve.setResPeoples(count);
					System.out.println(list.size());
					System.out.println(addReserve);
						if(list.size()!=0) {
							model.addAttribute("exist", "exist");
							}else {
								model.addAttribute("exist", "noExist");
								facilitiesService.addReserve(addReserve);
							}
					String referer = request.getHeader("Referer");
			        return "redirect:"+ referer;
				}
	
	// 즐겨찾기 등록
	@GetMapping("/favorite/{num}")
		public String addFavorite(@PathVariable("num") int num, HttpServletRequest request, HttpSession session, Model model) {
			Member member = (Member)session.getAttribute("member");

			
			log.info("아이디 : {}", member.getId());
			log.info("시설번호 : {}", num);
			
			facilitiesService.addFavorite(member.getId(), num);
			
			String referer = request.getHeader("Referer");
	        return "redirect:"+ referer;
		}
	// 즐겨찾기 삭제
	@GetMapping("/favorite/delete/{num}")
	public String deleteFavorite(@PathVariable("num") int num, HttpServletRequest request, HttpSession session, Model model) {
		Member member = (Member)session.getAttribute("member");
		
		facilitiesService.deleteFavorite(member.getId(), num);
		
		String referer = request.getHeader("Referer");
        return "redirect:"+ referer;
	}
	
	
	@GetMapping("/detail/{num}")
	public String findDetail(@PathVariable("num") int num, Model model, HttpSession session) {
		
		log.info("시설번호 : {}", num);
		if((Member)session.getAttribute("member") != null) {
		Member member = (Member)session.getAttribute("member");
		List<Favorite> list = facilitiesService.findFavoriteById(member.getId(), num);
		model.addAttribute("isExist", "noExist");
		for(Favorite favorite : list) {
			if(favorite.getId().equals(member.getId()) && favorite.getFacNum()==num) {
				model.addAttribute("isExist", "exist");
				}
			}
			Facilities facilityNum = facilitiesService.findByNum(num);
			model.addAttribute("facilityNum", facilityNum);
			model.addAttribute("existId", "existId");
			List<Review> reviewList = facilitiesService.findByReviewall(num);
			model.addAttribute("reviewList",reviewList);
			return "facilities/facility-detail-page";
		}else {
			Facilities facilityNum = facilitiesService.findByNum(num);
			model.addAttribute("facilityNum", facilityNum);
			model.addAttribute("existId", "noId");
			List<Review> reviewList = facilitiesService.findByReviewall(num);
			model.addAttribute("reviewList",reviewList);
			log.info("시설번호 : {}", reviewList);
			return "facilities/facility-detail-page";
		}
		
	}
	
	
	
	
	
	
	//리뷰 추가하기
 @PostMapping("/detail/review")
	public String addReview(@ModelAttribute Review review ,HttpSession httpsession, Model model, HttpServletRequest request) {
 		String referer = request.getHeader("referer");
 		
 		Member member = (Member) httpsession.getAttribute("member");
 		if(member == null) {
 			model.addAttribute("err","로그인 후에 작성해주세요  ");
 			model.addAttribute("referer", referer );
 			return "member/loginerr";
 		}
 		
 		List<Review> reviewList = facilitiesService.findByReviewall(review.getFacNum());
 		for (Review review2 : reviewList) {
				if(review2.getId().equals(member.getId())) {
	    			model.addAttribute("err","리뷰를 이미 작성하셨습니다 ");
	    			model.addAttribute("referer", referer );
	    			return "member/loginerr";
				}
			}
 	    
 		review.setId(member.getId());
 		System.out.println(review);
 		facilitiesService.addReview(review);
 		
	        
 		
 		return "redirect:" + referer;
	        
	        

	}
 
 
 
 
 
 @DeleteMapping("/delete/review/{id}")
 public String deleteReview(@PathVariable("id") String id,HttpServletRequest request) {
			String referer = request.getHeader("referer");
 	
 	   System.out.println("id = " + id);
        facilitiesService.deleteReview(id);
        return "redirect:"+referer;

 }
 
 @PostMapping("/edit/review/{reviewNum}")
 public String editReview(@PathVariable("reviewNum") int reviewNum, @ModelAttribute Review review,HttpServletRequest request ,HttpSession httpsession ) {
 	Member member = (Member) httpsession.getAttribute("member");
		String referer = request.getHeader("referer");
 	review.setId(member.getId());
     
 	review.setReviewNum(reviewNum);
 	System.out.println(review);
 	facilitiesService.editReview(review);
 	log.info("editReviwew:{}"+review);

     return "redirect:"+referer;
 }
 
 
// 
// @GetMapping("/list/hospital")
// public String findHospitalall(Model model) {
// 	List<Facilities> hospitalList = facilitiesService.findHospitalall();
// 	model.addAttribute("hospitalList", hospitalList);
// 	return "facilities/list-hospital";
// }
// 
 
 @GetMapping("/detail/hospital/{num}")
	public String findHospitalDetail(@PathVariable("num") int num, Model model) {
 	
		Facilities hospitalNum = facilitiesService.findByNum(num);
		//Facilities facilityDetail = facilitiesService.findDetail("24시 무인 애완용품 펫싸롱 4호점", "경기도 의정부시 평화로 689");
		model.addAttribute("hospitalNum", hospitalNum);
		
		//리뷰 전부 띄우기  
		List<Review> reviewList = facilitiesService.findByReviewall(num);
		model.addAttribute("reviewList",reviewList);
		log.info("시설번호 : {}", reviewList);
		
		return "facilities/hospital-detail-page";
	}
 
 //옵션별로 선택 안하고 전체 리스트 보기
	@GetMapping("/listpage/{num}")
	public String getListPage(Model model, @PathVariable("num") int num) {
		if (num != 0) {
			requestPage = num;
		}

		RequestParams params = new RequestParams(requestPage, elementSize, pageSize, null);
		int selectCount = facilitiesService.getListCount();

		Pagination pagination = new Pagination(params, selectCount);

		List<Facilities> list = null;
		list = facilitiesService.getListPage(num);
		model.addAttribute("facilityList", list);
		model.addAttribute("Params", params);
		model.addAttribute("Pagination", pagination);

		return "facilities/listFacilities";
	}
	
    //옵션별로 선택 안하고 전체 리스트 보기
	@GetMapping("/listpage/hospital/{num}")
	public String getListPageHospital2(Model model, @PathVariable("num") int num) {
		if (num != 0) {
			requestPage = num;
		}

		RequestParams params = new RequestParams(requestPage, elementSize, pageSize, null);
		int selectCount = facilitiesService.getListCountHospital();

		Pagination pagination = new Pagination(params, selectCount);

		List<Facilities> hospitalList = null;
		hospitalList = facilitiesService.getListHospital(num);
		model.addAttribute("hospitalList", hospitalList);
		model.addAttribute("Params", params);
		model.addAttribute("Pagination", pagination);

		return "facilities/list-hospital";
	}
	
	
	
	//옵션별로 선택했을때 페이징 처리
	@GetMapping("/list/all/{page}")
	public String findByCity2(@PathVariable int page, @ModelAttribute RequestParams requestparams, Model model) {
		if (page != 0) {
			requestPage = page;
		}
		requestparams.setRequestPage(requestPage);
		requestparams.setElementSize(elementSize);
		requestparams.setPageSize(pageSize);

		int selectCount = facilitiesService.getListCount2(requestparams);

		Pagination pagination = new Pagination(requestparams, selectCount);
		
		System.out.println(pagination);

		List<Facilities> list = null;
		
		list = facilitiesService.findByCity2(requestparams);

		model.addAttribute("facilityList", list);
		model.addAttribute("Params", requestparams);
		model.addAttribute("Pagination", pagination);
		
		return "facilities/listFacilities";
	}

	
	
	
	
	
	
	//옵션별로 선택했을때 페이징 처리
	@GetMapping("/list/all/hospital/{page}")
	public String findByCityHospital2(@PathVariable int page, @ModelAttribute RequestParams requestparams, Model model) {
		if (page != 0) {
			requestPage = page;
		}
		requestparams.setRequestPage(requestPage);
		requestparams.setElementSize(elementSize);
		requestparams.setPageSize(pageSize);

		int selectCount = facilitiesService.getListCountHospital2(requestparams);

		Pagination pagination = new Pagination(requestparams, selectCount);
		
		System.out.println(pagination);

		List<Facilities> hospitalList = null;
		
		hospitalList = facilitiesService.findByCityHospital2(requestparams);

		model.addAttribute("hospitalList", hospitalList);
		model.addAttribute("Params", requestparams);
		model.addAttribute("Pagination", pagination);
		
		return "facilities/list-hospital";
	}

	
	
	
}
