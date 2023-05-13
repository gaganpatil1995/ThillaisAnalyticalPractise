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
public class GetResResult {

	
	private String code ;
	private String benCode ;
	private String accNo ;
	private String ifsc ;
	private String name ;
	private String email ;
	private String mobile ;
	private String responseStatus ;
	private String successOrErrorMsg ;
	
	
}
