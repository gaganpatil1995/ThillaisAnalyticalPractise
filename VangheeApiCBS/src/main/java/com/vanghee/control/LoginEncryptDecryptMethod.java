package com.vanghee.control;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vanghee.model.Login;

public class LoginEncryptDecryptMethod {
	
	static String iv =  "94A149TH962A88DB" ;
	static String api = "115596THESH" ;
	static String encryptionkey = "9132THE96ABAABF7D3A0B4BE8B2D6GBE" ;
	
	
	public static void encryptionOfObject(Login obj) {
		
		String json ="";
		String encryptValue ="" ;
		String checkSum ="" ;
		ObjectMapper mapper = new ObjectMapper();
		try {
			json = mapper.writeValueAsString(obj);
			encryptValue = EncryptDecryptUtil.encryptAES256AndBase64(encryptionkey, iv, json);
			checkSum = EncryptDecryptUtil.createChecksum(json);
			
			System.out.println("Encrypted Value is : " +encryptValue) ;
			System.out.println("Check Sum is :" + checkSum) ;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void decryptValue(String value) {
		try {
			
			String str = EncryptDecryptUtil.decryptBase64EncodedAES256( encryptionkey, iv,value );
			System.out.println(str);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Login log = new Login("9789949018","NjYyNF85OwNDgwMV83ODE1=");
		
		encryptionOfObject(log) ;
		
		String value = "nm2WWyACguq9t8oGWksCAPn/E3mJt0sdesHMcuSZVnga8ZyGJD/axhwyMrq5beV/snYox8dBlK46OKtEQkoRujV0GLPRhcwG4ZvQj7Wx7BwsZGKjOvDjOicICKQObRMpHGO9a2c5H2CnJUBrVwBM4xQa3D+VuIYvsvi6rgHduKOHXu5ecjrURvpO9ZW4gUQi5BlCsmdIfk/mt1XTGqXKLNCaJ+6l6dpHVGbQSddE/SgZ0m1NHTyHoYK+jV1R1gVNxqZu04PEUvrBxm77ERznxnJ4uWcvejhSgwwfKrdsOGg6O1foPt7EPMfSiocj1WafRO7TrQK+b7SAKxd81LoglEqC54vwXyyC+IuelfLEfKMqJcf0ZR7HOeWHSHedcdBLRgMcOhP7EDxfszJlU/XkuuLAROJSGlwDQrIHYCPF45DC400GhSthe4A/tPKOi8gDLYLx07lrYX94RqmdG3Qq6M6fE2EGRXQb8tE0C3Sqrmr0dERaYMAHSW5HXRXDPu6kM54UucjgBbhwN04IBev4kidhurfkspcsSncsp8Qhy+XdxXBz1HLr4lWzGvfF6zbd" ;
		decryptValue(value) ;
	}
	

}
