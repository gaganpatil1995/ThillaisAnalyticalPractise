package com.vanghee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
 

@RestController
public class VangheeApiController {
	
	@Autowired
	BenDetailService benDetailService ;
	
	RestTemplate restTemplate = new RestTemplate() ;

	@PostMapping("/login")
	public  LoginResponse loginUser(@RequestBody Login login ) {
		String value = "" ;
		try {
		 value =  MainServices.httpMethodExecution("https://vangheeuat.com/aesvgnerp/api/cb/login", login);
		}catch(Exception e) {
			throw new RuntimeException("Please Enter Valid Credentials") ;
		}
		return (LoginResponse) MainServices.decryptObjectWithGeneric(value ,new LoginResponse());
		
		
	}
	
   @PostMapping("/add-Ben")
   public ServiceResponseData addBeneficiaryDetails(@RequestBody ServiceResponseData data ,@RequestParam String mobile , @RequestParam String urn) {
	  ObjectMapper mapper = new ObjectMapper() ;
	  AddBenResponse benResponse = null ;
	  ServiceResponseData serviceResponse = null ;
	   
	   try {
	   LoginResponse loginRes = loginUser(new Login(mobile,urn)) ;
	  if(loginRes.getResults()==null) {
		  serviceResponse = new ServiceResponseData() ;
		   serviceResponse.setCode(400) ;
		   serviceResponse.setMessage("Please Enter Valid Credentials") ;
		   return serviceResponse ;
	  }
	  LoginResponseResults loginResResult = loginRes.getResults() ;
	   
	    BenDetails benDetails = mapper.readValue(data.getValue(), BenDetails.class) ;
	   
	    
		if(benDetailService.getBenDetailsByAccountNo(benDetails.getAccNo()).isEmpty() == false) {
				   
				   serviceResponse = new ServiceResponseData() ;
				   serviceResponse.setCode(400) ;
				   serviceResponse.setMessage("Benificiary is Already present") ;
				   return serviceResponse ;
			   }
	   
	   benDetails.setUrnno(loginResResult.getId());
	   benDetails.setCorpUsrId(loginResResult.getId());
	   
	   String strObj = mapper.writeValueAsString(benDetails) ;
	   System.out.println(strObj) ;
	  
	   ResponseAndRequestDTO response = MainServices.httpMethodWithHeaders("https://vangheeuat.com/aesvgnerp/api/cb/add-ben",strObj , loginResResult);
	   
	    benResponse =   (AddBenResponse) MainServices.decryptObjectWithGeneric(response.getValue() ,new AddBenResponse());

	   
	    if(response.getCode() == 200) {
	    	
	    		benDetails.setBenCode(benResponse.getResults().getBenCode()) ;
		    	benDetailService.saveBeneficiaryDetails(benDetails);
		    	 System.out.println(benDetails) ;
	    	
	    	
	    }
	   
	   serviceResponse = MainServices.getServiceResponseData(response, benResponse) ;
	   
	  
	   }catch(Exception e) {
		   e.printStackTrace() ;
	   }
	   
	   return serviceResponse ;
   }
	
   

   @PostMapping("get-Ben")
   public ServiceResponseData getBeneficiaryDetails(@RequestBody ServiceResponseData data1 ,@RequestParam String mobile , @RequestParam String urn) {
	   String value ="" ; 
	   ServiceResponseData serviceResponse = new ServiceResponseData() ;
	   
	   try{
		   LoginResponseResults loginResponse = ( loginUser(new Login(mobile,urn))).getResults() ;
	      
	      ResponseAndRequestDTO response = MainServices.httpMethodWithHeaders("https://vangheeuat.com/aesvgnerp/api/cb/get-benbycode", data1.getValue(), loginResponse);
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
		   
		   ResponseAndRequestDTO response = MainServices.httpMethodWithHeaders("https://vangheeuat.com/aesvgnerp/calc-process-fee", strObj, loginResResult);
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
		    
		  ResponseAndRequestDTO response = MainServices.httpMethodWithHeaders("https://vangheeuat.com/aesvgnerp/reqauth-payment", strObj, loginResponse);
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
	   
      try {
    	  LoginResponseResults loginResponse = loginUser(new Login(mobile,urn)).getResults() ;
    	  if(loginResponse==null) {
    		  throw new RuntimeException("Please Enter Valid Credentials") ;
    	  }
	
	  ResponseAndRequestDTO response = MainServices.httpMethodWithHeaders("https://vangheeuat.com/aesvgnerp/verifyauth-payment", data.getValue(), loginResponse) ;
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
		
		  ResponseAndRequestDTO response = MainServices.httpMethodWithHeaders("https://vangheeuat.com/aesvgnerp/verifyauth-payment", data.getValue(), loginResponse) ;
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
		
		  ResponseAndRequestDTO response = MainServices.httpMethodWithHeaders("https://vangheeuat.com/aesvgnerp/verifyauth-payment", data.getValue(), loginResponse) ;
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
		
		  ResponseAndRequestDTO response = MainServices.httpMethodWithHeaders("https://vangheeuat.com/aesvgnerp/verifyauth-payment", data.getValue(), loginResponse) ;
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
		
		  ResponseAndRequestDTO response = MainServices.httpMethodWithHeaders("https://vangheeuat.com/aesvgnerp/verifyauth-payment", data.getValue(), loginResponse) ;
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
	  ScanQR qrScan = null;
	  LoginResponseResults loginResponse =null ;
	  String value = "" ;
	  ObjectMapper mapper = new ObjectMapper() ;
	try {
		 loginResponse = loginUser(new Login(mobile,urn)).getResults() ;
		 if(loginResponse==null) {
			 throw new RuntimeException("Please Enter Valid Credentials") ;
		 }
		 
		qrScan = mapper.readValue(data.getValue(), ScanQR.class);
		qrScan.setCustId(loginResponse.getId());
		String strObj = mapper.writeValueAsString(qrScan);
		ResponseAndRequestDTO response = MainServices.httpMethodWithHeaders("https://vangheeuat.com/aesvgnerp/qr-reqauth-payment\n", strObj, loginResponse);
		serviceResponse = MainServices.getServiceResponseData(response, MainServices.decryptObjectWithGeneric(response.getValue(), new Object()));
	    
	}  catch (Exception e) {
		 serviceResponse.setCode(400) ;
   	     serviceResponse.setMessage(e.getMessage());
	}
	  
	   
	 return serviceResponse;
   }
	
	
	
}
