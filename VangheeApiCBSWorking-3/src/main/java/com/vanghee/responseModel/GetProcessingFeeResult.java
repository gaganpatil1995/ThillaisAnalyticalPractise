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
public class GetProcessingFeeResult {
	
	private String payMode ;
	private String compId ;
	private String payAmount ;
	private String processingFee ;
	private String category ;
	private String custId ;
	
	
	
}
