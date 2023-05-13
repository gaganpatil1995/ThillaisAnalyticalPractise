package com.vanghee.model;

public class BenDetailDTO {
	
	private String compId ;
	private String benCode ;
	
	public BenDetailDTO(){
		
	}

	public BenDetailDTO(String compId, String benCode) {
		super();
		this.compId = compId;
		this.benCode = benCode;
	}

	public String getCompId() {
		return compId;
	}

	public void setCompId(String compId) {
		this.compId = compId;
	}

	public String getBenCode() {
		return benCode;
	}

	public void setBenCode(String benCode) {
		this.benCode = benCode;
	}

	
}
