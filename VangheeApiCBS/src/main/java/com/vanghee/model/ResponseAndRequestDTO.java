package com.vanghee.model;

public class ResponseAndRequestDTO {

	/*{
 "code": 200,
 "checkSum": "",
 "apiKey": "115596THESH",
 "errOrSuccessMessage": "Success",
 "value": "",
 "localDateTime": 1617883715075
}*/
	
	private String code ;
	private String checkSum ;
	private String apiKey ;
	private String errOrSuccessMessage ;
	private String value ;
	private String localDateTime ;
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCheckSum() {
		return checkSum;
	}
	public void setCheckSum(String checkSum) {
		this.checkSum = checkSum;
	}
	public String getApiKey() {
		return apiKey;
	}
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	public String getErrOrSuccessMessage() {
		return errOrSuccessMessage;
	}
	public void setErrOrSuccessMessage(String errOrSuccessMessage) {
		this.errOrSuccessMessage = errOrSuccessMessage;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getLocalDateTime() {
		return localDateTime;
	}
	public void setLocalDateTime(String localDateTime) {
		this.localDateTime = localDateTime;
	}
	
	
	
	
}
