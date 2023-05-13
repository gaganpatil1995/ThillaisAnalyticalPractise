package com.vanghee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vanghee.entity.BenDetails;
import com.vanghee.repository.BenDetailsRepository;

@Service
public class BenDetailServiceImpl implements BenDetailService {

	@Autowired
	BenDetailsRepository benDetailsDao ;
	
	@Override
	public List<BenDetails> getBenDetailsByAccountNo(String accNo) {
		Optional<List<BenDetails>> ben = benDetailsDao.findByAccNo(accNo) ;
		if(ben.isEmpty()) {
			return null ;
		}
		return ben.get() ;
		
	}

	@Override
	public List<BenDetails> getBenDetailsByMobileNo(String mobile) {
		
		Optional<List<BenDetails>> ben = benDetailsDao.findByMobile(mobile) ; 
		if(ben.isEmpty()) {
			return null ;
		}
		return ben.get() ;
	}

	@Override
	public BenDetails saveBeneficiaryDetails(BenDetails benDetails) {
		BenDetails beneficiary = benDetailsDao.save(benDetails);
		return beneficiary;
	}



	
}
