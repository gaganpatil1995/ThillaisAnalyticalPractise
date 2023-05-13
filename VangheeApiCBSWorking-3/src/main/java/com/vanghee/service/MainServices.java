package com.vanghee.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vanghee.model.ResponseAndRequestDTO;
import com.vanghee.responseModel.AddBenResponse;
import com.vanghee.responseModel.GetBenResponse;
import com.vanghee.responseModel.GetProcessingFee;
import com.vanghee.responseModel.LoginResponse;
import com.vanghee.responseModel.LoginResponseResults;
import com.vanghee.responseModel.PaymentTransferStatusResponse;
import com.vanghee.responseModel.ServiceResponseData;
import com.vanghee.responseModel.TxnAuthResponse;

public class MainServices {
	static RestTemplate restTemplate = new RestTemplate() ;
	static String iv =  "94A149TH962A88DB" ;
	static String api = "115596THESH" ;
	static String encryptionkey = "9132THE96ABAABF7D3A0B4BE8B2D6GBE" ;
	static ObjectMapper mapper = new ObjectMapper();
	
	
	public static ResponseAndRequestDTO encryptionOfObject(String strObj) {
		ResponseAndRequestDTO request = new ResponseAndRequestDTO() ;

		 
		try {
			request.setCode(200);
			request.setApiKey(api);
			request.setErrOrSuccessMessage("Success");
			
			request.setCheckSum(EncryptDecryptUtil.createChecksum(strObj));
			
			request.setValue(EncryptDecryptUtil.encryptAES256AndBase64(encryptionkey, iv, strObj));
			
			request.setLocalDateTime(1617883715075L);
		
		
		}catch(Exception e) {
			e.printStackTrace(); 
		}
		return request ;
	}
	

	
	public static Object decryptObjectWithGeneric(String value ,Object obj1) {
		String str = "" ;
		Object obj2 = null ;
		
		
		try {
			 str = EncryptDecryptUtil.decryptBase64EncodedAES256( encryptionkey, iv,value );
			 obj2  =  mapper.readValue(str, obj1.getClass()) ;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return obj2 ;
	}
	

	
	public static String httpMethodExecution(String url , Object obj ) {
		
		HttpEntity<ResponseAndRequestDTO> requestEntity = null ;
		
		try {
			 ResponseAndRequestDTO request = encryptionOfObject(mapper.writeValueAsString(obj)) ;
			 requestEntity = new HttpEntity<ResponseAndRequestDTO>(request) ;
		}catch(Exception e) {
			 e.printStackTrace();
		}
		
		return  restTemplate.exchange(url, HttpMethod.POST ,requestEntity ,ResponseAndRequestDTO.class).getBody().getValue() ;
		
	}
	
	public static ResponseAndRequestDTO httpMethodWithHeaders(String url , String value ,LoginResponseResults loginResponse) {
		
		if(loginResponse==null) {
			throw new RuntimeException("Please Enter Valid Cridential") ;
		}
		
			ResponseAndRequestDTO request = encryptionOfObject(value) ;
			HttpHeaders headers  = new HttpHeaders() ;
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set("authKey", loginResponse.getAuthKey());
			headers.set("Authorization", loginResponse.getTokenType()+" "+loginResponse.getToken());
			
			HttpEntity<ResponseAndRequestDTO> requestEntity = new HttpEntity<ResponseAndRequestDTO>(request ,headers) ;
		
		return  restTemplate.exchange(url, HttpMethod.POST ,requestEntity ,ResponseAndRequestDTO.class).getBody();
		
	}
	public static ServiceResponseData getServiceResponseData( ResponseAndRequestDTO response ,Object obj) {
		ServiceResponseData serviceResponse = new ServiceResponseData() ;
		serviceResponse.setCode(response.getCode());
		
		if(response.getCode() != 200) {
			serviceResponse.setMessage(response.getErrOrSuccessMessage());
		}
		else {
			try{
			
			String value = mapper.writeValueAsString(obj)  ;
			serviceResponse.setValue(value);
			serviceResponse.setMessage("Succes");
			
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return serviceResponse ;
	}
	

}
