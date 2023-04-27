package com.vanghee.control;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vanghee.model.BenDetailDTO;
import com.vanghee.model.EncryptedValue;

public class GetBenDetails {

	static String iv =  "94A149TH962A88DB" ;
	static String api = "115596THESH" ;
	static String encryptionkey = "9132THE96ABAABF7D3A0B4BE8B2D6GBE" ;
	
	public static EncryptedValue encryptBeneficiaryDetailDTO(BenDetailDTO benDetail) {
		ObjectMapper mapper = new ObjectMapper() ;
		String json = "" ;
		String value = "" ;
		String checkSum = "" ;
		
		try {
			
			json = mapper.writeValueAsString(benDetail) ;
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
		 BenDetailDTO benDetail = new BenDetailDTO() ;
		 benDetail.setCompId("115639");
		 benDetail.setBenCode("7541471");
		EncryptedValue encryptValue = encryptBeneficiaryDetailDTO(benDetail);
		System.out.println("Check Sum - "+encryptValue.getCheckSum()) ;
		System.out.println("Value - "+ encryptValue.getValue()) ;
		
		System.out.println();
		
		System.out.println();
		
		String value = "nm2WWyACguq9t8oGWksCAPn/E3mJt0sdesHMcuSZVnga8ZyGJD/axhwyMrq5beV/snYox8dBlK46OKtEQkoRuhpW873qcg9JJRgl1nxdM"
				+ "ZbdSb9JyIdnUbump1h+DED0BdI5uky9111Er/49pu007GwCaLbHLEBEvsaIctg2ouXXL3ahyq3IS6aiVhrPwzOPNIVxN1lEDEvpLjj5bWK9HJYKX"
				+ "sFMVsF0dWwtZUgco/DuZEti9TyRNwVr9Pp0wfHDo3QZpxogUYxaEC7cwxiaUbWjqiyhFr+sJfbhO0bQi0C2duodLl64MpP31Kzqw9IgA+Hy6LPAVQ"
				+ "Ir84O8/4g8diMHEEXR2Uo9zUj2qp0VW4pKylIADjPtRpHjxypUS7cv" ;
	   System.out.println(decryptResponse(value));
	 }
}
