package com.vanghee.control;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vanghee.model.BenDetailDTO;
import com.vanghee.model.BenDetails;
import com.vanghee.model.CallBackApprovalDTO;
import com.vanghee.model.CreateVirtualAccNoDTO;
import com.vanghee.model.EcollectionInwardReportDTO;
import com.vanghee.model.EncryptedValue;
import com.vanghee.model.Login;
import com.vanghee.model.PaymentTransferStatusDTO;
import com.vanghee.model.ProcessingFeeDTO;
import com.vanghee.model.ResponseAndRequestDTO;
import com.vanghee.model.ScanQR;
import com.vanghee.model.TxnAuthenticationDTO;
import com.vanghee.model.UpiCollectionInwardReportDTO;

public class MainController {
	
	static String iv =  "94A149TH962A88DB" ;
	static String api = "115596THESH" ;
	static String encryptionkey = "9132THE96ABAABF7D3A0B4BE8B2D6GBE" ;
	
	
	public static ResponseAndRequestDTO encryptionOfLoginObject(Login obj) {
		
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
		ResponseAndRequestDTO request = new ResponseAndRequestDTO() ;
		
		request.setCode("200");
		request.setApiKey(api);
		request.setErrOrSuccessMessage("Success");
		request.setCheckSum(checkSum);
		request.setValue(encryptValue);
		
		return request ;
		
	}
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
	public static EncryptedValue encryptUpiCollectionInwardReportDTO(UpiCollectionInwardReportDTO upiCollect) {
		ObjectMapper mapper = new ObjectMapper() ;
		String json = "" ;
		String value = "" ;
		String checkSum = "" ;
		
		try {
			
			json = mapper.writeValueAsString(upiCollect) ;
			checkSum = EncryptDecryptUtil.createChecksum(json) ;
			value = EncryptDecryptUtil.encryptAES256AndBase64(encryptionkey, iv, json);
		}catch(Exception e) {
			e.printStackTrace(); 
		}
		return new EncryptedValue(checkSum,value) ;
	 }
	
	public static EncryptedValue encryptScanQR(ScanQR scanQR) {
		ObjectMapper mapper = new ObjectMapper() ;
		String json = "" ;
		String value = "" ;
		String checkSum = "" ;
		
		try {
			
			json = mapper.writeValueAsString(scanQR) ;
			checkSum = EncryptDecryptUtil.createChecksum(json) ;
			value = EncryptDecryptUtil.encryptAES256AndBase64(encryptionkey, iv, json);
		}catch(Exception e) {
			e.printStackTrace(); 
		}
		return new EncryptedValue(checkSum,value) ;
	 }
	
	public static EncryptedValue encryptPaymentTransferStatusDTO(PaymentTransferStatusDTO paymentTranfer) {
		ObjectMapper mapper = new ObjectMapper() ;
		String json = "" ;
		String value = "" ;
		String checkSum = "" ;
		
		try {
			
			json = mapper.writeValueAsString(paymentTranfer) ;
			checkSum = EncryptDecryptUtil.createChecksum(json) ;
			value = EncryptDecryptUtil.encryptAES256AndBase64(encryptionkey, iv, json);
		}catch(Exception e) {
			e.printStackTrace(); 
		}
		return new EncryptedValue(checkSum,value) ;
	 }
	
	public static EncryptedValue encryptEcollectionInwardReportDTO(EcollectionInwardReportDTO eCollection) {
		ObjectMapper mapper = new ObjectMapper() ;
		String json = "" ;
		String value = "" ;
		String checkSum = "" ;
		
		try {
			
			json = mapper.writeValueAsString(eCollection) ;
			checkSum = EncryptDecryptUtil.createChecksum(json) ;
			value = EncryptDecryptUtil.encryptAES256AndBase64(encryptionkey, iv, json);
		}catch(Exception e) {
			e.printStackTrace(); 
		}
		return new EncryptedValue(checkSum,value) ;
	 }
	
	public static EncryptedValue encryptCreateVirtualAccNoDTO(CreateVirtualAccNoDTO virtualAccNo) {
		ObjectMapper mapper = new ObjectMapper() ;
		String json = "" ;
		String value = "" ;
		String checkSum = "" ;
		
		try {
			
			json = mapper.writeValueAsString(virtualAccNo) ;
			checkSum = EncryptDecryptUtil.createChecksum(json) ;
			value = EncryptDecryptUtil.encryptAES256AndBase64(encryptionkey, iv, json);
		}catch(Exception e) {
			e.printStackTrace(); 
		}
		return new EncryptedValue(checkSum,value) ;
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

}
