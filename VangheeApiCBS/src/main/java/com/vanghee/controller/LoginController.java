package com.vanghee.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vanghee.model.EncryptedValue;
import com.vanghee.model.Login;
import com.vanghee.util.EncryptDecryptUtil;

@RestController
public class LoginController {

	@GetMapping("/encrypt")
	public EncryptedValue getEncryptedValue(@RequestBody Login login ) {
		
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		String checkSum ="" ;
		String value = "";
		
		try {
		  json = mapper.writeValueAsString(login);

		 checkSum = EncryptDecryptUtil.createChecksum(json);

		  value = EncryptDecryptUtil.encryptAES256AndBase64("9132THE96ABAABF7D3A0B4BE8B2D6GBE", "94A149TH962A88DB", json);
				  System.out.println("Check sum for given object"+checkSum);
		          System.out.println("Value of encryptedData"+value);
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new EncryptedValue(checkSum ,value) ;
		
	}
	
	@GetMapping("/decrypt")
	public String getDecryptedDate(@RequestParam String value) {
		String responseObj= "";
		try {
			responseObj = EncryptDecryptUtil.decryptBase64EncodedAES256("9132THE96ABAABF7D3A0B4BE8B2D6GBE","94A149TH962A88DB", value);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return responseObj ;
	}
	

	
	
	
}
