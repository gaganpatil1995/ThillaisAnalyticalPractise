package com.vanghee.responseModel;

public class TxnAuthResResult {
	/*{"responseStatus":"Success","responseCode":200,"message":"Your request is waiting for approval",
	"results":{"status":"Success","message":"Transaction Initiated","response":"Success","uniqueId":"6477270","utrNo":""}}*/
	
	private String status ;
	private String message ;
	private String response ;
	private String uniqueId ;
	private String utrNo ;
	
	public String getStatus() {
		return status ;
	}
	public void setStatus(String status) {
		this.status = status ;
	}
	public String getMessage() {
		return message ;
	}
	public void setMessage(String message) {
		this.message = message ;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public String getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	public String getUtrNo() {
		return utrNo;
	}
	public void setUtrNo(String utrNo) {
		this.utrNo = utrNo;
	}
    
}
