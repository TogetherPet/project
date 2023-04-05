package together.pet.web.facilities.controller;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;
import together.pet.domain.facilities.dto.Facilities;
import together.pet.domain.facilities.service.FacilitiesService;

@Controller
@RequestMapping("/facilities")
@Slf4j
public class FacilitiesController {
	
	@Autowired
	FacilitiesService facilitiesService;
	
	@GetMapping("/list")
	public String findAll(Model model) {
		List<Facilities> facilityList = facilitiesService.findAll();
		model.addAttribute("facilityList", facilityList);
		return "facilities/listFacilities";
	}
	
	@GetMapping("/detail/{num}")
	public String findDetail(@PathVariable("num") int num, Model model) {
		log.info("시설번호 : {}", num);
		Facilities facilityNum = facilitiesService.findByNum(num);
		//Facilities facilityDetail = facilitiesService.findDetail("24시 무인 애완용품 펫싸롱 4호점", "경기도 의정부시 평화로 689");
		model.addAttribute("facilityNum", facilityNum);
		return "facilities/facility-detail-page";
	}
	
	
}
