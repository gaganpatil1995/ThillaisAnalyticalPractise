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
		
		String value = "nm2WWyACguq9t8oGWksCAPn/E3mJt0sdesHMcuSZVnga8ZyGJD/axhwyMrq5beV/snYox8dBlK46OKtEQkoRujV0GLPRhcwG4ZvQj7Wx7BwsZGKjOvDjOicICKQObRMpHGO9a2c5H2CnJUBrVwBM4xQa3D+VuIYvsvi6rgHduKPfy7t0KbGzzM5Ell8ZzLINeX5OQz6GMLFY+zmFv/xHokCTOFa44kGDR8a4e1UOMYQJOPyFcBKdYGmdhO5Qw8iQqwpCyCJgPLR1v5pgaywbVJQrpF4+f4wlf7soGnER669zfAQ4v2pWA4ccibt1UuoJJQvui0dWnHuF0dRfSZBwl2vOmI/yFz1mSz0dGbjk63EfHFEc4hE55U34QDiFO2AHRSnH/XEcUfpgTov+jfWOMMAzvOnc5zDqXTta74Ui/AiJEWz8I77fSHa0Mu7BpJorRMPaXhNyp6m8k/tYahAErH7Jyx018Sp1okbMUyu3lDPt7BGoCXo7MX1hd9Olin9tJ8tW6115gJpda1wti66b87pkGby6WKDFSO5RsnLT7WCZwEOAco5BHAOGQqOriHkt" ;
		decryptValue(value) ;
	}
	

}
