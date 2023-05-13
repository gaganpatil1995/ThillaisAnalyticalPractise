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
public class TxnAuthResResult {
	
	private String status ;
	private String message ;
	private String response ;
	private String uniqueId ;
	private String utrNo ;
	
	
}
