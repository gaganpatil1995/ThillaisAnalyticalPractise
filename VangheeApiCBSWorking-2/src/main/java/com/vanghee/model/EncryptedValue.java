package com.vanghee.model;




public class EncryptedValue {
	
	
	private String checkSum ;
	private String value ;
	
	public EncryptedValue() {
		
	}
	public EncryptedValue(String checkSum , String value) {
		this.checkSum = checkSum ;
		this.value = value ;
	}
	public String getCheckSum() {
		return checkSum;
	}
	public void setCheckSum(String checkSum) {
		this.checkSum = checkSum;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	

}
