package com.vanghee.control;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vanghee.model.CallBackApprovalDTO;
import com.vanghee.model.EncryptedValue;
import com.vanghee.model.TxnAuthenticationDTO;

public class PostCallBackApproval {

	static String iv =  "94A149TH962A88DB" ;
	static String api = "115596THESH" ;
	static String encryptionkey = "9132THE96ABAABF7D3A0B4BE8B2D6GBE" ;
	
	public static EncryptedValue encryptCallBackApprovalDTO(CallBackApprovalDTO callBack) {
		ObjectMapper mapper = new ObjectMapper() ;
		String json = "" ;
		String value = "" ;
		String checkSum = "" ;
		
		try {
			
			json = mapper.writeValueAsString(callBack) ;
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
		
		CallBackApprovalDTO callBack = new CallBackApprovalDTO(); 
		callBack.setRecId("A111");
		callBack.setUniqueId("6477269");
		callBack.setAmount("14000");
		callBack.setStatus("PAY-APPROVED");
		callBack.setApprovalcode("1001");
		callBack.setCompId("115639");
		callBack.setCryptoCode("MTY0NF8xMfMjg5MTlfNTM1MMTY0NF8xMfMjg5MTlfNTM1MQ===");
		callBack.setFiller1(" FUTURE USE ");
		callBack.setFiller2(" FUTURE USE ");
		callBack.setFiller3(" FUTURE USE ");
		callBack.setFiller4(" FUTURE USE ");
		callBack.setFiller5(" FUTURE USE ");
		callBack.setFiller6(" FUTURE USE ");
		
EncryptedValue encryptValue = encryptCallBackApprovalDTO(callBack);
		
		System.out.println("Check Sum - "+encryptValue.getCheckSum()) ;
		System.out.println("Value - "+ encryptValue.getValue() +"\n"+"\n") ;
		

		
		   String value = "nm2WWyACguq9t8oGWksCAGR2QvrTFOCmGOEUhzu60Yapn2pn2qJfJqTnvXXcMTjYPbuwJlorMdVpMB2bQYv5INWV7p75hZps3IiplT7UjrKgwfzSyi9Ie0WaEfsFTJ1rVC95/V9Em4uudLMg8eL9fMMoJE55plm5n2m3vl71XLD2KUNqaFCvUxbXEyuVz9vRjdeoDE0EryWLHUuUdLdd5udGFcETfVzY/aD8Ma68/f+Ut8UFyMi63Bh+Pg1flm2t" ;
		   System.out.println(decryptResponse(value));
		
		
		
	}
}
