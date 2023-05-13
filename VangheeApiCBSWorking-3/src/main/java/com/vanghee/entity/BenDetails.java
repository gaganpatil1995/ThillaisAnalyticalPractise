package com.vanghee.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class BenDetails {
   
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer benDetailId ;
	
	private String accNo ;
	private String corpUsrId ;
	private String urnno ;
	private String email ;
	private String ifscCode ;
	private String mobile ;
	private String name ;
	private String vpa ;
	private String compId ;
	private String benCode ;
	
	
	public BenDetails(String accNo, String corpUsrId, String urnno, String email, String ifscCode, String mobile,
			String name, String vpa, String compId, String benCode) {
		super();
		this.accNo = accNo;
		this.corpUsrId = corpUsrId;
		this.urnno = urnno;
		this.email = email;
		this.ifscCode = ifscCode;
		this.mobile = mobile;
		this.name = name;
		this.vpa = vpa;
		this.compId = compId;
		this.benCode = benCode;
	}  
	
	public BenDetails() {
		
	}

	public String getAccNo() {
		return accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	public String getCorpUsrId() {
		return corpUsrId;
	}

	public void setCorpUsrId(String corpUsrId) {
		this.corpUsrId = corpUsrId;
	}

	public String getUrnno() {
		return urnno;
	}

	public void setUrnno(String urnno) {
		this.urnno = urnno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVpa() {
		return vpa;
	}

	public void setVpa(String vpa) {
		this.vpa = vpa;
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

	@Override
	public String toString() {
		return "BenDetails [benDetailId=" + benDetailId + ", accNo=" + accNo + ", corpUsrId=" + corpUsrId + ", urnno="
				+ urnno + ", email=" + email + ", ifscCode=" + ifscCode + ", mobile=" + mobile + ", name=" + name
				+ ", vpa=" + vpa + ", compId=" + compId + ", benCode=" + benCode + "]";
	}
	
	
}
