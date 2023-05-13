package com.vanghee.responseModel;

public class GetBenResponse {

	
	private String responseStatus ;
	private String responseCode ;
	private String message ;
	private GetResResult results ;
	
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
	public GetResResult getResults() {
		return results;
	}
	public void setResults(GetResResult results) {
		this.results = results;
	}
	
	
	
}
