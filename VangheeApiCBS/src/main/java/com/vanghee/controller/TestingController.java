package com.vanghee.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vanghee.model.Login;
import com.vanghee.util.EncryptDecryptUtil;

@RestController
public class TestingController {

	@GetMapping("/entript")
	public String getEncryptedValue(@RequestBody Login login ) {
		
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		String checkSum ="" ;
		
		try {
		  json = mapper.writeValueAsString(login);

		 checkSum = EncryptDecryptUtil.createChecksum(json);
		  System.out.println(checkSum);
		  
		  
			
		}catch (JsonProcessingException e) {
		   e.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return checkSum ;
		
	}

	
	
	
}
