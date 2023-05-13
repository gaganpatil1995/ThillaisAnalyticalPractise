package com.vanghee.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vanghee.entity.BenDetails;
import com.vanghee.model.BenDetailDTO;
import com.vanghee.model.CallBackApprovalDTO;
import com.vanghee.model.CreateVirtualAccNoDTO;
import com.vanghee.model.EcollectionInwardReportDTO;
import com.vanghee.model.Login;
import com.vanghee.model.PaymentTransferStatusDTO;
import com.vanghee.model.ProcessingFeeDTO;
import com.vanghee.model.ResponseAndRequestDTO;
import com.vanghee.model.ScanQR;
import com.vanghee.model.TxnAuthenticationDTO;
import com.vanghee.model.UpiCollectionInwardReportDTO;
import com.vanghee.responseModel.AddBenResponse;
import com.vanghee.responseModel.GetBenResponse;
import com.vanghee.responseModel.GetProcessingFee;
import com.vanghee.responseModel.LoginResponse;
import com.vanghee.responseModel.LoginResponseResults;
import com.vanghee.responseModel.PaymentTransferStatusResponse;
import com.vanghee.responseModel.TxnAuthResponse;

public class MainServices {
	static RestTemplate restTemplate = new RestTemplate() ;
	static String iv =  "94A149TH962A88DB" ;
	static String api = "115596THESH" ;
	static String encryptionkey = "9132THE96ABAABF7D3A0B4BE8B2D6GBE" ;
	static ObjectMapper mapper = new ObjectMapper();
	
	
	public static ResponseAndRequestDTO encryptionOfObject(Object obj) {
		ResponseAndRequestDTO request = new ResponseAndRequestDTO() ;
		String json = "" ;
		 
		try {
			
			json = mapper.writeValueAsString(obj) ;
			request.setCode(200);
			request.setApiKey(api);
			request.setErrOrSuccessMessage("Success");
			request.setCheckSum(EncryptDecryptUtil.createChecksum(json));
			request.setValue(EncryptDecryptUtil.encryptAES256AndBase64(encryptionkey, iv, json));
			request.setLocalDateTime(1617883715075L);
		}catch(Exception e) {
			e.printStackTrace(); 
		}
		return request ;
	}
	
	public static LoginResponse decryptLoginResponseValue(String value) {
		String str = "" ;
		LoginResponse loginResponse = null ;
		try {
			
			 str = EncryptDecryptUtil.decryptBase64EncodedAES256( encryptionkey, iv,value );
			 
			 loginResponse =  mapper.readValue(str, LoginResponse.class) ;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return loginResponse ;
	}
	
	
	
	
	public static AddBenResponse decryptAddBenResponse(String value) {
		String str = "" ;
		AddBenResponse addBenResponse = null ;
		try {
			
			 str = EncryptDecryptUtil.decryptBase64EncodedAES256( encryptionkey, iv,value );
			 addBenResponse = mapper.readValue(str, AddBenResponse.class) ;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return addBenResponse ;
	}
	
	
	
	public static GetBenResponse decryptGetBenDetailsValue(String value) {
		String str = "" ;
		GetBenResponse getBenResponse = null ;
		try {
			
			 str = EncryptDecryptUtil.decryptBase64EncodedAES256( encryptionkey, iv,value );
			 getBenResponse = mapper.readValue(str, GetBenResponse.class) ;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getBenResponse ;
	}
	
	
	
	public static GetProcessingFee decryptGetProcessingFeeResponse(String value) {
		String str = "" ;
		GetProcessingFee processingFeeRes = null ; 
		try {
			
			 str = EncryptDecryptUtil.decryptBase64EncodedAES256( encryptionkey, iv,value );
			 processingFeeRes = mapper.readValue(str, GetProcessingFee.class) ;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return processingFeeRes ;
	}
	
	
	
	public static TxnAuthResponse decryptTxnAuthResponse(String value) {
		String str = "" ;
		TxnAuthResponse txnAuthRes = null ;
		try {
			
			 str = EncryptDecryptUtil.decryptBase64EncodedAES256( encryptionkey, iv,value );
			 txnAuthRes = mapper.readValue(str, TxnAuthResponse.class) ;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return txnAuthRes ;
	}
	
	
	
	
	
	public static PaymentTransferStatusResponse decryptPaymentTranferStatusResponse(String value) {
		String str = "" ;
		PaymentTransferStatusResponse payTransRes = null ;
		try {
			
			 str = EncryptDecryptUtil.decryptBase64EncodedAES256( encryptionkey, iv,value );
			 payTransRes = mapper.readValue(str, PaymentTransferStatusResponse.class) ;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return payTransRes ;
	}
	
	
	public static String httpMethodExecution(String url , Object obj ) {
		ResponseAndRequestDTO request = encryptionOfObject(obj) ;
		HttpEntity<ResponseAndRequestDTO> requestEntity = new HttpEntity<ResponseAndRequestDTO>(request) ;
		return  restTemplate.exchange(url, HttpMethod.POST ,requestEntity ,ResponseAndRequestDTO.class).getBody().getValue() ;
		
	}
	
	
	public static String httpMethodWithHeaders(String url , Object obj ,LoginResponseResults loginResponse) {
		ResponseAndRequestDTO request = encryptionOfObject(obj) ;
		HttpHeaders headers  = new HttpHeaders() ;
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("authKey", loginResponse.getAuthKey());
		headers.set("Authorization", loginResponse.getTokenType()+" "+loginResponse.getToken());
		
		HttpEntity<ResponseAndRequestDTO> requestEntity = new HttpEntity<ResponseAndRequestDTO>(request ,headers) ;
		
		return  restTemplate.exchange(url, HttpMethod.POST ,requestEntity ,ResponseAndRequestDTO.class).getBody().getValue() ;
		
	}
	
	
	
	public static String decryptValue(String value) {
		String str = "" ;
		try {
			
			 str = EncryptDecryptUtil.decryptBase64EncodedAES256( encryptionkey, iv,value );
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str ;
	}
	

}
