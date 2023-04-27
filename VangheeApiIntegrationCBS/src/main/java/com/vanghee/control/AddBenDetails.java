package com.vanghee.control;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vanghee.model.BenDetails;
import com.vanghee.model.EncryptedValue;

public class AddBenDetails {

	static String iv =  "94A149TH962A88DB" ;
	static String api = "115596THESH" ;
	static String encryptionkey = "9132THE96ABAABF7D3A0B4BE8B2D6GBE" ;
	
	public static EncryptedValue encryptBeneficiaryDetail(BenDetails benDetail) {
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
		 BenDetails benDetails = new BenDetails();
		 benDetails.setAccNo("223344343");
		 benDetails.setCompId("115639");
		 benDetails.setUrnno("OTk2M185OwODgwMV8yNDgy=");
		 benDetails.setCorpUsrId("OTk2M185OwODgwMV8yNDgy=");
		 benDetails.setEmail("verrapan1881@gmail.com");
		 benDetails.setIfscCode("SBI0711");
		 benDetails.setMobile("9900877656");
		 benDetails.setName("Veerapan vishwas2");
		 benDetails.setVpa("9909098094");
		 
		 EncryptedValue encryptValue = encryptBeneficiaryDetail(benDetails);
		 
		 System.out.println("Check Sum - "+ encryptValue.getCheckSum()) ;
		 System.out.println("Value - "+ encryptValue.getValue()) ;
		 
		 System.out.println();
		 
		 String value = "nm2WWyACguq9t8oGWksCAPn/E3mJt0sdesHMcuSZVnga8ZyGJD/axhwyMrq5beV/snYox8dBlK46OKtEQkoRuu5DjaPP92qYxLOV"
		 		+ "ADMYvVenMv4N+7Owvd2lKw/DvI11qxATzs5HF1qoZ7+VilLxbi9F+jw/K1pSWr7Sb9QQUSQvfJetiG/x+PpqiUnkUVaCz9dGZny"
		 		+ "PQqmcYCNr/5tZOwj5Qy2R9uVlRCTKBz9z+7fhTizB+wwxBAyrL05hbWMcbBEmujR6yA4pFDikEaZg2J+TNtpaozbAqHuGgWTXSPQ=";
		 
		 System.out.println(decryptResponse(value));
	 }
}
