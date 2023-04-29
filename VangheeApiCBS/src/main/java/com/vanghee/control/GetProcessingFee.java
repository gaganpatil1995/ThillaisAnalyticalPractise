package com.vanghee.control;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vanghee.model.BenDetailDTO;
import com.vanghee.model.EncryptedValue;
import com.vanghee.model.ProcessingFeeDTO;

public class GetProcessingFee {

	static String iv =  "94A149TH962A88DB" ;
	static String api = "115596THESH" ;
	static String encryptionkey = "9132THE96ABAABF7D3A0B4BE8B2D6GBE" ;
	
	public static EncryptedValue encryptProcessingFeeDTO(ProcessingFeeDTO prcessingFeeDTO) {
		ObjectMapper mapper = new ObjectMapper() ;
		String json = "" ;
		String value = "" ;
		String checkSum = "" ;
		
		try {
			
			json = mapper.writeValueAsString(prcessingFeeDTO) ;
			checkSum = EncryptDecryptUtil.createChecksum(json) ;
			value = EncryptDecryptUtil.encryptAES256AndBase64(encryptionkey, iv, json);
		}catch(Exception e) {
			e.printStackTrace(); 
		}
		return new EncryptedValue(checkSum,value) ;
	 }
	
 public static String decryptResponse(String value) {
		 
		 String stringObject = "" ;
		 try {
				
			 stringObject = EncryptDecryptUtil.decryptBase64EncodedAES256( encryptionkey, iv,value );
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 return stringObject ;
	 }
	
	public static void main(String[] args) {
		 ProcessingFeeDTO processingFeeDTO = new ProcessingFeeDTO() ;
		processingFeeDTO.setCompId("115639");
		processingFeeDTO.setCategory("PAYMENTS");
		processingFeeDTO.setPayMode("NEFT");
		processingFeeDTO.setPayAmount("145678");
		processingFeeDTO.setCustId("NjQ3NF85OwNDgwMV85MzU3=");
		 
		 
		EncryptedValue encryptValue = encryptProcessingFeeDTO(processingFeeDTO);
		System.out.println("Check Sum - "+encryptValue.getCheckSum()) ;
		System.out.println("Value - "+ encryptValue.getValue()) ;
		
		System.out.println();
		
		System.out.println();
		
		String value = "nm2WWyACguq9t8oGWksCAPn/E3mJt0sdesHMcuSZVnga8ZyGJD/axhwyMrq5beV/2P7D7mtgRJcjwQulJbGXSByfBVkcAqL3oEycq0Bg6SpccxaLwIbmtrxK3LAAsewU4r53H7Rl14gslW3Nva9bgufWTn7d89CObUMaP/ueAien/hpearNy+YhYCe4ZbsBFE/HJwbVVwQi39wnwh8SYXtVrhzWK0U6izwH9l1jdJpAeDroPwPgfFFVF5j7ZNCuMh9eNaNlgVg25xFPCScsf/g2Yt0l0qxCGAvBNzJDoeto=" ;
	   System.out.println(decryptResponse(value));
	 }
}
