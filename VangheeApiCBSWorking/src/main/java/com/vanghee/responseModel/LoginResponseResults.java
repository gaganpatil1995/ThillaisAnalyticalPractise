package com.vanghee.responseModel;

/*{"responseStatus":"Success","responseCode":200,"message":"","results":
{"token":"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI5Nzg5OTQ5MDE4IiwiaWF0IjoxNjgyNzY5NTQ3LCJleHAiOjE2ODI3NzA0NDd9.axG_wyKMsmDovIgPSUQT2ZD5wMXvUiyOjZQF6BMnm950awxcmdkRdxX-CdM36jD24pATbl8qPSJNaq_sCDQVqg",
"id":"ODk3Nl85OwODgwMV8wMTQ4=","tokenType":"Bearer","message":"Success","expiry":"Apr 29, 2023 5:44:07 PM",
"authKey":"+8fvV5w6FdaByerw1olUnvP/VRG6PYIqyPZbCP52o+c="}}*/

public class LoginResponseResults {

	
	private String token ;
	private String id ;
	private String tokenType ;
	private String message ;
	private String expiry ;
	private String authKey ;
	
	public LoginResponseResults() {
		
	}
	
	public LoginResponseResults(String token, String id, String tokenType, String message, String expiry,
			String authKey) {
		super();
		this.token = token;
		this.id = id;
		this.tokenType = tokenType;
		this.message = message;
		this.expiry = expiry;
		this.authKey = authKey;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTokenType() {
		return tokenType;
	}
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}
	public String getExpiry() {
		return expiry;
	}
	public void setExpiry(String expiry) {
		this.expiry = expiry;
	}
	public String getAuthKey() {
		return authKey;
	}
	public void setAuthKey(String authKey) {
		this.authKey = authKey;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
