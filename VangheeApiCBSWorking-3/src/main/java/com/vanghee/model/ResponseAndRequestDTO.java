package com.vanghee.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseAndRequestDTO {


	
	private Integer code ;
	private String checkSum ;
	private String apiKey ;
	private String errOrSuccessMessage ;
	private String value ;
	private Long localDateTime ;
	
	
}
