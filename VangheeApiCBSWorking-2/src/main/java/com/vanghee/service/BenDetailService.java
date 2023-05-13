package com.vanghee.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vanghee.entity.BenDetails;

@Service
public interface BenDetailService {

	public List<BenDetails> getBenDetailsByAccountNo(String accNo);
	public List<BenDetails> getBenDetailsByMobileNo(String mobile) ;
	public BenDetails saveBeneficiaryDetails(BenDetails benDetails) ;
}
