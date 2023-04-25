package com.vanghee.model;

public class Login {

	private String mobile ;
	private String urn ;
	
	
	
	public  Login(String mobile, String urn) {
		super();
		this.mobile = mobile;
		this.urn = urn;
	}
	public  Login(){
		
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getUrn() {
		return urn;
	}
	public void setUrn(String urn) {
		this.urn = urn;
	}
	@Override
	public String toString() {
		return "{mobile=" + mobile + ", urn=" + urn + "}";
	}
	
	
}
