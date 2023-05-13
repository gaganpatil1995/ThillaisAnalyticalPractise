package com.vanghee.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScanQR {
	
	
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
	
	

}
