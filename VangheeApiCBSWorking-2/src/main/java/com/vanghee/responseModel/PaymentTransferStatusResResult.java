package com.vanghee.responseModel;

public class PaymentTransferStatusResResult {
	/*{"responseStatus":"Success","responseCode":200,"message":"",
"results":{"txnId":"7449","status":"Pending","responseStatus":"Amount Transfer is pending for TXN ID : 7449"}}*/

	private String txnId ;
	private String status ;
	private String responseStatus ;
	public String getTxnId() {
		return txnId;
	}
	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getResponseStatus() {
		return responseStatus;
	}
	public void setResponseStatus(String responseStatus) {
		this.responseStatus = responseStatus;
	}
	
	
}
