package com.vanghee.responseModel;
/*{"responseStatus":"Success","responseCode":200,"message":"","results":
{"token":"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI5Nzg5OTQ5MDE4IiwiaWF0IjoxNjgyNzY5NTQ3LCJleHAiOjE2ODI3NzA0NDd9.axG_wyKMsmDovIgPSUQT2ZD5wMXvUiyOjZQF6BMnm950awxcmdkRdxX-CdM36jD24pATbl8qPSJNaq_sCDQVqg",
"id":"ODk3Nl85OwODgwMV8wMTQ4=","tokenType":"Bearer","message":"Success","expiry":"Apr 29, 2023 5:44:07 PM",
"authKey":"+8fvV5w6FdaByerw1olUnvP/VRG6PYIqyPZbCP52o+c="}}*/

public class LoginResponse {

	private String responseStatus ;
	private String responseCode ;
	private String message ;
	private LoginResponseResults results ;
	
	
	
	public LoginResponse(String responseStatus, String responseCode, String message, LoginResponseResults results) {
		super();
		this.responseStatus = responseStatus;
		this.responseCode = responseCode;
		this.message = message;
		this.results = results;
	}
	
	public LoginResponse() {
		
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
	public LoginResponseResults getResults() {
		return results;
	}
	public void setResults(LoginResponseResults results) {
		this.results = results;
	}
	
	
	
	
	
}
