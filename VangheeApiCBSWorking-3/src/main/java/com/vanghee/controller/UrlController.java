package com.vanghee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vanghee.entity.UrlDetails;
import com.vanghee.service.UrlDetailsService;

@RestController
public class UrlController {
	
	  @Autowired
      UrlDetailsService urlService ;
	
	@PostMapping("/saveUrl")
	public ResponseEntity<UrlDetails> saveUrlDetails(@RequestBody UrlDetails urlDetails){
		
		  return new ResponseEntity<>(urlService.saveUrlDetails(urlDetails),HttpStatus.ACCEPTED) ;
	}
	@GetMapping("/getUrl")
	public ResponseEntity<UrlDetails> getUrlDetailsByUrlName(@RequestParam String urlName){
		
		return new ResponseEntity<>(urlService.getUrlDetailsByUrlName(urlName),HttpStatus.FOUND) ;
	}
	
	@DeleteMapping("/delUrl")
	public ResponseEntity<String> removeUrlDetailsById(@RequestParam Integer urlId){
		return new ResponseEntity<String>(urlService.removeUrlDetails(urlId), HttpStatus.OK) ;
	}
}
