package com.vanghee.controller;

import java.io.DataInput;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
import com.vanghee.model.ScanQR;
import com.vanghee.model.TxnAuthenticationDTO;
import com.vanghee.model.UpiCollectionInwardReportDTO;
import com.vanghee.responseModel.AddBenResponse;
import com.vanghee.responseModel.GetBenResponse;
import com.vanghee.responseModel.GetProcessingFee;
import com.vanghee.responseModel.LoginResponse;
import com.vanghee.responseModel.LoginResponseResults;
import com.vanghee.responseModel.ServiceResponseData;
import com.vanghee.responseModel.TxnAuthResponse;
import com.vanghee.service.BenDetailService;
import com.vanghee.service.MainServices;
 

@RestController
public class VangheeApiController {
	
	@Autowired
	BenDetailService benDetailService ;
	
	RestTemplate restTemplate = new RestTemplate() ;

	@PostMapping("/login")
	public  LoginResponse loginUser(@RequestBody Login login ) {
		 
		
		String value =  MainServices.httpMethodExecution("https://vangheeuat.com/aesvgnerp/api/cb/login", login);
	
		return MainServices.decryptLoginResponseValue(value);
		
		
	}
//	@PostMapping("/login")
//	public  LoginResponse loginUser(@RequestBody ServiceResponseData data ) {
//		 Login login = new ObjectMapper().readValue(data.getValue() , Login.class);
//		
//		String value =  MainServices.httpMethodExecution("https://vangheeuat.com/aesvgnerp/api/cb/login", login);
//	
//		return MainServices.decryptLoginResponseValue(value);
//		
//		
//	}
	
   @PostMapping("/add-Ben")
   public AddBenResponse addBeneficiaryDetails(@RequestBody BenDetails benDetails ,@RequestParam String mobile , @RequestParam String urn) {
	  
	   if(benDetailService.getBenDetailsByAccountNo(benDetails.getAccNo()).isEmpty() == false) {
		   
		   throw new RuntimeException("Beneficiary Already present") ;
	   }
	   
	   LoginResponseResults loginResResult = loginUser(new Login(mobile,urn)).getResults() ;
	   
	   benDetails.setUrnno(loginResResult.getId());
	   benDetails.setCorpUsrId(loginResResult.getId());
	  
	   String value = MainServices.httpMethodWithHeaders("https://vangheeuat.com/aesvgnerp/api/cb/add-ben", benDetails, loginResResult);
	   
	   AddBenResponse benResponse = MainServices.decryptAddBenResponse(value);
	   
	   benDetails.setBenCode(benResponse.getResults().getBenCode()) ;
	   
	   benDetailService.saveBeneficiaryDetails(benDetails);
	   
	   return benResponse ;
   }
	
   
   @PostMapping("get-Ben")
   public GetBenResponse getBeneficiaryDetails(@RequestBody BenDetailDTO benDetails ,@RequestParam String mobile , @RequestParam String urn) {
	   LoginResponseResults loginResponse = loginUser(new Login(mobile,urn)).getResults() ;
	      
	    
	     String value = MainServices.httpMethodWithHeaders("https://vangheeuat.com/aesvgnerp/api/cb/get-benbycode", benDetails, loginResponse);
	    
	 return MainServices.decryptGetBenDetailsValue(value) ;
   }
   
   
   @PostMapping("/processFee")
   public GetProcessingFee getProcessingFee(@RequestBody ProcessingFeeDTO processingFee ,@RequestParam String mobile ,@RequestParam String urn) {
	   LoginResponseResults loginResResult = loginUser(new Login(mobile,urn)).getResults() ;
	   processingFee.setCustId(loginResResult.getId()) ;
	   
	  String value = MainServices.httpMethodWithHeaders("https://vangheeuat.com/aesvgnerp/calc-process-fee", processingFee, loginResResult);
	   return MainServices.decryptGetProcessingFeeResponse(value);
   }

   
   @PostMapping("/reqauth-payment")
   public TxnAuthResponse initiateTxnAuthentication(@RequestBody TxnAuthenticationDTO txnAuth,@RequestParam String mobile , @RequestParam String urn) {
	   LoginResponseResults loginResponse = loginUser(new Login(mobile,urn)).getResults() ;
	    txnAuth.setCustId(loginResponse.getId());
	    
	  String value = MainServices.httpMethodWithHeaders("https://vangheeuat.com/aesvgnerp/reqauth-payment", txnAuth, loginResponse);
	   return MainServices.decryptTxnAuthResponse(value) ;
   }
   
   
   
   @PostMapping("/verifyauth-payment")
   public String callBackApprovalRequest(@RequestBody CallBackApprovalDTO approvalRequest,@RequestParam String mobile , @RequestParam String urn) {
	   LoginResponseResults loginResponse = loginUser(new Login(mobile,urn)).getResults() ;
    
	
	   String value = MainServices.httpMethodWithHeaders("https://vangheeuat.com/aesvgnerp/verifyauth-payment", approvalRequest, loginResponse) ;
	  
	   return MainServices.decryptValue(value);
   }
   
   
   
   @PostMapping("pay-status")
   public String getPaymentTransferStatus(@RequestBody PaymentTransferStatusDTO payTrnsStatusDto,@RequestParam String mobile , @RequestParam String urn) {
	   
	   LoginResponseResults loginResponse = loginUser(new Login(mobile,urn)).getResults() ;
	   
	 String value = MainServices.httpMethodWithHeaders("https://vangheeuat.com/aesvgnerp/api/cb/pay/status", payTrnsStatusDto, loginResponse);
	   return MainServices.decryptValue(value) ;
   }
   
   
   
   @PostMapping("/vir-acc")
   public String createVirtualAccount(@RequestBody CreateVirtualAccNoDTO virAcc,@RequestParam String mobile , @RequestParam String urn) {
	   LoginResponseResults loginResponse = loginUser(new Login(mobile,urn)).getResults() ;
	  
	 String value = MainServices.httpMethodWithHeaders("https://vangheeuat.com/aesvgnerp/api/cb/cust/master", virAcc, loginResponse);
	   return MainServices.decryptValue(value) ;
   }
   
   
   
   @PostMapping("/getEcollection")
   public String getEcollectionInward(@RequestBody EcollectionInwardReportDTO eCollect,@RequestParam String mobile , @RequestParam String urn) {
	   LoginResponseResults loginResponse = loginUser(new Login(mobile,urn)).getResults() ;
	   
	  String value = MainServices.httpMethodWithHeaders("https://vangheeuat.com/aesvgnerp/api/cb/mis", eCollect, loginResponse);
	   
	   return MainServices.decryptValue(value) ;
   }
   
   @PostMapping("/upiCollect")
   public String upiCollectionInward(@RequestBody UpiCollectionInwardReportDTO upiCollect ,@RequestParam String mobile ,@RequestParam String urn) {
	   LoginResponseResults loginResponse = loginUser(new Login(mobile,urn)).getResults() ;
	  
	   String value = MainServices.httpMethodWithHeaders("https://vangheeuat.com/aesvgnerp/api/cb/misupicollection", upiCollect, loginResponse);
	   
	   return MainServices.decryptValue(value) ;
   }
   
   
    
   @PostMapping("/qrScan")
   public String qrScanTransaction(@RequestBody ScanQR qrScan ,@RequestParam String mobile ,@RequestParam String urn) {
	   LoginResponseResults loginResponse = loginUser(new Login(mobile,urn)).getResults() ;
	   
	   qrScan.setCustId(loginResponse.getId());
	   
	   String value = MainServices.httpMethodWithHeaders("https://vangheeuat.com/aesvgnerp/qr-reqauth-payment\n", qrScan, loginResponse);
	   
	   return MainServices.decryptValue(value) ;
   }
	
	
	
}
