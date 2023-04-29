package com.vanghee.control;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vanghee.model.EncryptedValue;
import com.vanghee.model.TxnAuthenticationDTO;

public class InitiateTxnAuthentication {
	
	static String iv =  "94A149TH962A88DB" ;
	static String api = "115596THESH" ;
	static String encryptionkey = "9132THE96ABAABF7D3A0B4BE8B2D6GBE" ;
	
	public static EncryptedValue encryptTxnAuthenticationDTO(TxnAuthenticationDTO txnAuth) {
		ObjectMapper mapper = new ObjectMapper() ;
		String json = "" ;
		String value = "" ;
		String checkSum = "" ;
		
		try {
			
			json = mapper.writeValueAsString(txnAuth) ;
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
	
	public static void main(String[] arg) {
		TxnAuthenticationDTO txnAuth = new TxnAuthenticationDTO() ;
		
		txnAuth.setCustId("NDg5Ml85OwNDgwMV8yNTQz=");
		txnAuth.setCompId("115639");
		txnAuth.setCustName("Narayan");
		txnAuth.setBenName("Veerapan vishwas");
		txnAuth.setAmount("14000");
		txnAuth.setProcessingFee("0.00");
		txnAuth.setPayMode("NEFT");
		txnAuth.setBankId("493");
		txnAuth.setBenCode("7541471");
		txnAuth.setBenAccNo("22334452");
		txnAuth.setRemarks("test payment");
		txnAuth.setSrc("CBS001");
		txnAuth.setRecId("A1234");
		txnAuth.setMemberMobile("9789949018");
		txnAuth.setCallbackurl("http://cbs-callbackurl.com/paymentverify");
		
		EncryptedValue encryptValue = encryptTxnAuthenticationDTO(txnAuth);
		
		System.out.println("Check Sum - "+encryptValue.getCheckSum()) ;
		System.out.println("Value - "+ encryptValue.getValue()) ;
		
		System.out.println();
		
		System.out.println();
		
		String value = "nm2WWyACguq9t8oGWksCAPn/E3mJt0sdesHMcuSZVnga8ZyGJD/axhwyMrq5beV/g1hANnndH4UFmXLYE77d742zENrABTpGWV1BqpEx0FFVrpLOcv3kFq24+7zm0MMVQXtJAQ2dPgIIDTQS5Iwx2QUmjJ5ySYqkxc4JinshAEtfnDMSOiiIx4e86ssfNG79VpVgWQZfKLMu23ovulvxy4Cj6H+D6wCcTSGnvLX4bZm6+2Z/I4p4+129w2aXHcNrIebWjxPtQhNmDWao4NLttIi0yXyklFkSB8bb7BFbAMU=" ;
		   System.out.println(decryptResponse(value));
		
	}

}
