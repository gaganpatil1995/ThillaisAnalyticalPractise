package com.vanghee.model;

public class ScanQR {
	
	/*"custId": "MzMxNl8xMzMDQzXzU0NTM==", // Replace with Your Id received from Login token API
	"compId": "168",
	"custName": "Abc Company",
	"benName": "XYZ Company", // Name Scanned from QR code
	"amount": "1000.00",
	"processingFee":"2.36", //Processing Fee are calculated against each Payment Slab shared with CBS.
	"payMode": "UPI", //UPI
	"bankId": "394",
	"vpa": "abc@ICIC", //VPA Address Scanned from QR code
	"remarks": "test payment",
	"src":"CBS001"
	"recId":"A1234" // UNIQUE TXN ID GENERATED AT CBS END.
	"memberMobile": "7012122525" // 10 Digit Mobile number Member
	"callbackurl": "http://cbs-callbackurl.com/paymentverify"
*/
	
	private String custId ;
	private String compId ;
	private String custName ;
	private String benName ;
	private String amount ;
	private String processingFee ;
	private String payMode ;
	private String bankId ;
	private String vpa ;
	private String remarks ;
	private String src ;
	private String recId ;
	private String memberMobile ;
	private String callbackurl ;
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getCompId() {
		return compId;
	}
	public void setCompId(String compId) {
		this.compId = compId;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getBenName() {
		return benName;
	}
	public void setBenName(String benName) {
		this.benName = benName;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getProcessingFee() {
		return processingFee;
	}
	public void setProcessingFee(String processingFee) {
		this.processingFee = processingFee;
	}
	public String getPayMode() {
		return payMode;
	}
	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	public String getVpa() {
		return vpa;
	}
	public void setVpa(String vpa) {
		this.vpa = vpa;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public String getRecId() {
		return recId;
	}
	public void setRecId(String recId) {
		this.recId = recId;
	}
	public String getMemberMobile() {
		return memberMobile;
	}
	public void setMemberMobile(String memberMobile) {
		this.memberMobile = memberMobile;
	}
	public String getCallbackurl() {
		return callbackurl;
	}
	public void setCallbackurl(String callbackurl) {
		this.callbackurl = callbackurl;
	}
	
	 

}
