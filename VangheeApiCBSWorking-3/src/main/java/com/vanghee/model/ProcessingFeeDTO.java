package com.vanghee.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProcessingFeeDTO {
	
	private String custId ;
	private String payMode ;
	private String category ;
	private String compId ;
	private String payAmount ;


}
