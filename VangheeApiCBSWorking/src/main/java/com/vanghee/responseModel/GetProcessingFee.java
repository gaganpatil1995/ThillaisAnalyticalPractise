package com.vanghee.responseModel;

public class GetProcessingFee {

	
	private String responseStatus ;
	private String responseCode ;
	private String message ;
	private GetProcessingFeeResult results ;
	
	public GetProcessingFee() {
		
	}
	public GetProcessingFee(String responseStatus, String responseCode, String message,
			GetProcessingFeeResult results) {
		super();
		this.responseStatus = responseStatus;
		this.responseCode = responseCode;
		this.message = message;
		this.results = results;
	}
	public String getResponseStatus() {
		return responseStatus;
	}
	public void setResponseStatus(String responseStatus) {
		this.responseStatus = responseStatus;
	}
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public GetProcessingFeeResult getResults() {
		return results;
	}
	public void setResults(GetProcessingFeeResult results) {
		this.results = results;
	}
	
	
	
	
	
}
