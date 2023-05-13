package com.vanghee.responseModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.vanghee.model.BenDetailDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentTransferStatusResResult {

	private String txnId ;
	private String status ;
	private String responseStatus ;
	

}
