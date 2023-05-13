package com.vanghee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vanghee.entity.BenDetails;
import com.vanghee.model.Login;
import com.vanghee.model.ProcessingFeeDTO;
import com.vanghee.model.ResponseAndRequestDTO;
import com.vanghee.model.ScanQR;
import com.vanghee.model.TxnAuthenticationDTO;
import com.vanghee.responseModel.AddBenResponse;
import com.vanghee.responseModel.GetBenResponse;
import com.vanghee.responseModel.GetProcessingFee;
import com.vanghee.responseModel.LoginResponse;
import com.vanghee.responseModel.LoginResponseResults;
import com.vanghee.responseModel.ServiceResponseData;
import com.vanghee.responseModel.TxnAuthResponse;
import com.vanghee.service.BenDetailService;
import com.vanghee.service.MainServices;
import com.vanghee.service.UrlDetailsService;
 

 @RestController
public class VangheeApiController {
	
	@Autowired
	BenDetailService benDetailService ;
	@Autowired
	UrlDetailsService urlService ;
	
	ObjectMapper mapper = new ObjectMapper() ;
	 
	@PostMapping("/login")
	public  LoginResponse loginUser(@RequestBody Login login ){
		String value = "" ;
		try {
			String url = urlService.getUrlDetailsByUrlName("VangheeLogin").getUrl() ;
		    value =  MainServices.httpMethodExecution(url, login);
		}catch(Exception e) {
			throw new RuntimeException("Please Enter Valid Credentials") ;
		}
		return (LoginResponse) MainServices.decryptObjectWithGeneric(value ,new LoginResponse());
		
		
	} 
	
   @PostMapping("/add-Ben")
   public ServiceResponseData addBeneficiaryDetails(@RequestBody ServiceResponseData data ,@RequestParam String mobile , @RequestParam String urn) {
	  
	   
	  	  ServiceResponseData serviceResponse = new ServiceResponseData() ; 
	   
	   try {
		   LoginResponseResults loginResResult = loginUser(new Login(mobile,urn)).getResults() ;
	 
		   if(loginResResult==null) {
			   throw new RuntimeException("Please Enter Valid Credential") ;
		   }
	  
	   
	    BenDetails benDetails = mapper.readValue(data.getValue(), BenDetails.class) ;
	   
	    
	    if(benDetailService.getBenDetailsByAccountNo(benDetails.getAccNo()).isEmpty() == false) {
			
				   throw new RuntimeException("Benificiary Already present") ;
			   
	     }
	   
	     benDetails.setUrnno(loginResResult.getId());
	     benDetails.setCorpUsrId(loginResResult.getId());
	   
	     String strObj = mapper.writeValueAsString(benDetails) ;
	     String url = urlService.getUrlDetailsByUrlName("AddBen").getUrl() ;
	    
	     ResponseAndRequestDTO response = MainServices.httpMethodWithHeaders(url,strObj , loginResResult);
	   
	     AddBenResponse benResponse =   (AddBenResponse) MainServices.decryptObjectWithGeneric(response.getValue() ,new AddBenResponse());
 
	   
	    if(response.getCode() == 200) {
	    	
	    		benDetails.setBenCode(benResponse.getResults().getBenCode()) ;
		    	benDetailService.saveBeneficiaryDetails(benDetails);
		    
	    }
	    
	    benResponse.getResults().setCorpUsrId(null);
	    benResponse.getResults().setCode(null) ;
	    
	    serviceResponse = MainServices.getServiceResponseData(response, benResponse) ;
	   
	  
	   }catch(Exception e) {
		     serviceResponse.setCode(400);
		     serviceResponse.setMessage(e.getMessage());
		     //serviceResponse.setResponse(urn)
		     return serviceResponse ;
	   }
	   
	   return serviceResponse ;
   }
	
   

   @PostMapping("get-Ben")
   public ServiceResponseData getBeneficiaryDetails(@RequestBody ServiceResponseData data1 ,@RequestParam String mobile , @RequestParam String urn) {
	   String value ="" ; 
	   ServiceResponseData serviceResponse = new ServiceResponseData() ;
	   
	   try{
		   LoginResponseResults loginResponse = ( loginUser(new Login(mobile,urn))).getResults() ;
		   if(loginResponse==null) {
			   throw new RuntimeException("Please Enter Valid Credential") ;
		   }
		   String url = urlService.getUrlDetailsByUrlName("GetBen").getUrl() ;
	      ResponseAndRequestDTO response = MainServices.httpMethodWithHeaders(url, data1.getValue(), loginResponse);
	      GetBenResponse getBenRes = (GetBenResponse) MainServices.decryptObjectWithGeneric(response.getValue(),new GetBenResponse()) ;
	      
	      serviceResponse = MainServices.getServiceResponseData(response, getBenRes) ;
	      
	     }catch(Exception e){
	     serviceResponse.setCode(400);
	     serviceResponse.setMessage(e.getMessage());
	     return serviceResponse ;
	    
	     }
	
	 return serviceResponse ;
   }
   
   
   @PostMapping("/processFee")
   public Object getProcessingFee(@RequestBody ServiceResponseData data ,@RequestParam String mobile ,@RequestParam String urn) {
	   ObjectMapper mapper = new ObjectMapper() ;
	   ServiceResponseData serviceResponse = new ServiceResponseData() ;
	   try {
		   LoginResponseResults loginResResult = loginUser(new Login(mobile,urn)).getResults() ;
		   if(loginResResult==null) {
			   throw new RuntimeException("Please Enter Valid Credential") ;
		   }
		   ProcessingFeeDTO processingFee = mapper.readValue(data.getValue(), ProcessingFeeDTO.class) ;
		   processingFee.setCustId(loginResResult.getId()) ;
		   String strObj = mapper.writeValueAsString(processingFee) ;
		   
		   String url = urlService.getUrlDetailsByUrlName("ProcessFee").getUrl() ;
		   ResponseAndRequestDTO response = MainServices.httpMethodWithHeaders(url, strObj, loginResResult);
		   GetProcessingFee getProcessRes = (GetProcessingFee) MainServices.decryptObjectWithGeneric(response.getValue(),new GetProcessingFee());
		   serviceResponse = MainServices.getServiceResponseData(response, getProcessRes) ;
		  
	   }catch(Exception e) {
		   serviceResponse.setCode(400) ;
		   serviceResponse.setMessage(e.getMessage()) ;
 	   }
	   return serviceResponse ;
   }

   
   @PostMapping("/reqauth-payment")
   public Object initiateTxnAuthentication(@RequestBody ServiceResponseData data,@RequestParam String mobile , @RequestParam String urn) {
	   ObjectMapper mapper = new ObjectMapper() ;
	   ServiceResponseData serviceResponse = new ServiceResponseData() ;
	
	   try {
		  LoginResponseResults loginResponse = loginUser(new Login(mobile,urn)).getResults() ;
		  if(loginResponse==null) {
			  throw new RuntimeException("Please Enter Valid Credentials") ;
		  }
		  TxnAuthenticationDTO txnAuth = mapper.readValue(data.getValue(), TxnAuthenticationDTO.class) ;  
		  txnAuth.setCustId(loginResponse.getId());
		  String strObj = mapper.writeValueAsString(txnAuth) ;
		  
		  String url = urlService.getUrlDetailsByUrlName("Reqauth-payment").getUrl() ;
		  ResponseAndRequestDTO response = MainServices.httpMethodWithHeaders(url, strObj, loginResponse);
		  serviceResponse = MainServices.getServiceResponseData(response, MainServices.decryptObjectWithGeneric(response.getValue(), new TxnAuthResponse()));
		  
	  }catch(Exception e) {
		  serviceResponse.setCode(400) ;
		  serviceResponse.setMessage(e.getMessage());
	  }
	   
	   return serviceResponse;
   }
   
   
   
   @PostMapping("/verifyauth-payment")
   public Object callBackApprovalRequest(@RequestBody ServiceResponseData data,@RequestParam String mobile , @RequestParam String urn) {
	   ServiceResponseData serviceResponse = new ServiceResponseData() ;
	   
       try{
    	  LoginResponseResults loginResponse = loginUser(new Login(mobile,urn)).getResults() ;
    	  if(loginResponse==null) {
    		  throw new RuntimeException("Please Enter Valid Credentials") ;
    	  }
    	  String url = urlService.getUrlDetailsByUrlName("Verifyauth-payment").getUrl() ;
	      ResponseAndRequestDTO response = MainServices.httpMethodWithHeaders(url, data.getValue(), loginResponse) ;
	      serviceResponse = MainServices.getServiceResponseData(response, MainServices.decryptObjectWithGeneric(response.getValue(), new Object()));
        }catch(Exception e) {
    	  serviceResponse.setCode(400) ;
    	  serviceResponse.setMessage(e.getMessage());
        }
	   return serviceResponse ;
   }
   
   
   
   @PostMapping("pay-status")
   public Object getPaymentTransferStatus(@RequestBody ServiceResponseData data,@RequestParam String mobile , @RequestParam String urn) {
	     ServiceResponseData serviceResponse = new ServiceResponseData() ;
	   
	      try {
	    	  LoginResponseResults loginResponse = loginUser(new Login(mobile,urn)).getResults() ;
	    	  if(loginResponse==null) {
	    		  throw new RuntimeException("Please Enter Valid Credentials") ;
	    	  }
		     
	    	  String url = urlService.getUrlDetailsByUrlName("Pay-status").getUrl() ;
		      ResponseAndRequestDTO response = MainServices.httpMethodWithHeaders(url, data.getValue(), loginResponse) ;
		      serviceResponse = MainServices.getServiceResponseData(response, MainServices.decryptObjectWithGeneric(response.getValue(), new Object()));
	      }catch(Exception e) {
	    	  serviceResponse.setCode(400) ;
	    	  serviceResponse.setMessage(e.getMessage());
	      }
		   return serviceResponse ;
   }
   
   
   
   @PostMapping("/vir-acc")
   public Object createVirtualAccount(@RequestBody ServiceResponseData data,@RequestParam String mobile , @RequestParam String urn) {
	      ServiceResponseData serviceResponse = new ServiceResponseData() ;
	   
	      try {
	    	  LoginResponseResults loginResponse = loginUser(new Login(mobile,urn)).getResults() ;
	    	  if(loginResponse==null) {
	    		  throw new RuntimeException("Please Enter Valid Credentials") ;
	    	  }
	    	  String url = urlService.getUrlDetailsByUrlName("Vir-acc").getUrl() ;
		      ResponseAndRequestDTO response = MainServices.httpMethodWithHeaders(url, data.getValue(), loginResponse) ;
		      serviceResponse = MainServices.getServiceResponseData(response, MainServices.decryptObjectWithGeneric(response.getValue(), new Object()));
	      }catch(Exception e) {
	    	  serviceResponse.setCode(400) ;
	    	  serviceResponse.setMessage(e.getMessage());
	      }
		   return serviceResponse ;
   }
   
   
   
   @PostMapping("/getEcollection")
   public Object getEcollectionInward(@RequestBody ServiceResponseData data,@RequestParam String mobile , @RequestParam String urn) {
	      ServiceResponseData serviceResponse = new ServiceResponseData() ;
	   
	      try {
	    	  LoginResponseResults loginResponse = loginUser(new Login(mobile,urn)).getResults() ;
	    	  if(loginResponse==null) {
	    		  throw new RuntimeException("Please Enter Valid Credentials") ;
	    	  }
		     
	    	  String url = urlService.getUrlDetailsByUrlName("GetEcollection").getUrl() ;
		      ResponseAndRequestDTO response = MainServices.httpMethodWithHeaders(url, data.getValue(), loginResponse) ;
		      serviceResponse = MainServices.getServiceResponseData(response, MainServices.decryptObjectWithGeneric(response.getValue(), new Object()));
	      }catch(Exception e) {
	    	  serviceResponse.setCode(400) ;
	    	  serviceResponse.setMessage(e.getMessage());
	      }
		   return serviceResponse ;
   }
   
   @PostMapping("/upiCollect")
   public Object upiCollectionInward(@RequestBody ServiceResponseData data ,@RequestParam String mobile ,@RequestParam String urn) {
	      ServiceResponseData serviceResponse = new ServiceResponseData() ;
	   
	      try {
	    	  LoginResponseResults loginResponse = loginUser(new Login(mobile,urn)).getResults() ;
	    	  if(loginResponse==null) {
	    		  throw new RuntimeException("Please Enter Valid Credentials") ;
	    	  }
		       
	    	  String url = urlService.getUrlDetailsByUrlName("UpiCollect").getUrl() ;
		      ResponseAndRequestDTO response = MainServices.httpMethodWithHeaders(url, data.getValue(), loginResponse) ;
		      serviceResponse = MainServices.getServiceResponseData(response, MainServices.decryptObjectWithGeneric(response.getValue(), new Object()));
	      }catch(Exception e) {
	    	  serviceResponse.setCode(400) ;
	    	  serviceResponse.setMessage(e.getMessage());
	      }
		   return serviceResponse ;
   }
   
   
    
   @PostMapping("/qrScan")
   public Object qrScanTransaction(@RequestBody ServiceResponseData data ,@RequestParam String mobile ,@RequestParam String urn) {
	  ServiceResponseData serviceResponse = new ServiceResponseData() ;
	
	  ObjectMapper mapper = new ObjectMapper() ;
	  try {
		  LoginResponseResults loginResponse = loginUser(new Login(mobile,urn)).getResults() ;
		  if(loginResponse==null) {
			 throw new RuntimeException("Please Enter Valid Credentials") ;
		  }
		 
		  ScanQR  qrScan = mapper.readValue(data.getValue(), ScanQR.class);
		  qrScan.setCustId(loginResponse.getId());
		  String strObj = mapper.writeValueAsString(qrScan);
		  
		  String url = urlService.getUrlDetailsByUrlName("QrScan").getUrl() ;
		  ResponseAndRequestDTO response = MainServices.httpMethodWithHeaders(url, strObj, loginResponse);
		  serviceResponse = MainServices.getServiceResponseData(response, MainServices.decryptObjectWithGeneric(response.getValue(), new Object()));
	    
	}  catch (Exception e) {
		 serviceResponse.setCode(400) ;
   	     serviceResponse.setMessage(e.getMessage());
	}
	  
	   
	 return serviceResponse;
   }
	
	
	
}
