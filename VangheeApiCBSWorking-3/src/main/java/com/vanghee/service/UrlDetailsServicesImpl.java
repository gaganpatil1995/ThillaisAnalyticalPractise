package com.vanghee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vanghee.entity.UrlDetails;
import com.vanghee.repository.UrlDetailsRepository;

@Service
public class UrlDetailsServicesImpl implements UrlDetailsService{

	@Autowired
	UrlDetailsRepository urlDetailsDao ;
	
	@Override
	public UrlDetails getUrlDetailsByUrlName(String urlName) {
		Optional<List<UrlDetails>> opt = urlDetailsDao.findByUrlName(urlName);
		if(opt.isEmpty()){
			throw new RuntimeException("No Record Found") ;
		}
		List<UrlDetails> listUrl = opt.get() ;
		
		if(listUrl.isEmpty()) {
			throw new RuntimeException("No Record Found") ;
		}
		
		return listUrl.get(0);
	}

	@Override
	public UrlDetails getUrlDetailsById(Integer urlId) {
		Optional<UrlDetails> opt = urlDetailsDao.findById(urlId) ;
		if(opt.isEmpty()) {
			throw new RuntimeException("Please Enter Valid URL ID") ;
		}
		return opt.get();
	}

	@Override
	public UrlDetails saveUrlDetails(UrlDetails urlDetails) {
		Optional<List<UrlDetails>> opt = urlDetailsDao.findByUrlName(urlDetails.getUrlName());
		if(!opt.isEmpty()) {
			if(opt.get().isEmpty()==false) {
				throw new RuntimeException("Url is Already Present") ;
			}
			
		}
		return urlDetailsDao.save(urlDetails);
	}

	@Override
	public String removeUrlDetails(Integer urlId) {
	    Optional<UrlDetails> opt = urlDetailsDao.findById(urlId) ;
	    if(opt.isEmpty()) {
	    	throw new RuntimeException("Please Enter valid URL ID") ;
	    }
	    urlDetailsDao.delete(opt.get());
		return "Given Url Details Is Remove Successfully - "+urlId;
	}

}
