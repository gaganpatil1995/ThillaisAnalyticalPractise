package com.vanghee.model;



public class ProcessingFeeDTO {
	/*"custId": "MzMxNl8xMzMDQzXzU0NTM==", // Replace with
	Your Id received from Login token API
	"payMode": "NEFT",
	"category": "PAYMENTS",
	"compId": "123",
	"payAmount": "14567.00"
*/
	
	private String custId ;
	private String payMode ;
	private String category ;
	private String compId ;
	private String payAmount ;
	
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getPayMode() {
		return payMode;
	}
	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCompId() {
		return compId;
	}
	public void setCompId(String compId) {
		this.compId = compId;
	}
	public String getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(String payAmount) {
		this.payAmount = payAmount;
	}
	
	

}
