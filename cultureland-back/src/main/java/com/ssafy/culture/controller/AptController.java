package com.ssafy.culture.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.culture.dto.AptDeal;
import com.ssafy.culture.dto.AptInfo;
import com.ssafy.culture.service.AptService;

@RequestMapping("/apt")
@RestController
public class AptController {
	
	private AptService aptService;

	@Autowired
	public AptController(AptService aptService) {
		super();
		this.aptService = aptService;
	}
	
	@GetMapping("/dong/{dongCode}")
	public ResponseEntity<?> getAptInfoByDongCode(@PathVariable long dongCode) throws Exception {
		System.out.println(dongCode);
		List<AptInfo> infos = null;
		if(dongCode > 0) {
			infos = aptService.getAptInfoByDongCode(dongCode);
		}
		
		return new ResponseEntity<List<AptInfo>>(infos, HttpStatus.OK);
	}
	
	@GetMapping("/apt/{aptCode}")
	public ResponseEntity<?> getAptDealByAptCode(@PathVariable long aptCode) throws Exception {
		System.out.println(aptCode);
		List<AptDeal> deals = null;
		
		if(aptCode > 0) {
			deals = aptService.getAptDealAptCode(aptCode);
		}
		
		return new ResponseEntity<List<AptDeal>>(deals, HttpStatus.OK);
	}
}
