package com.vanghee.service;

import org.springframework.stereotype.Service;

import com.vanghee.entity.UrlDetails;

@Service
public interface UrlDetailsService {

	public UrlDetails getUrlDetailsByUrlName(String urlName) ;
	public UrlDetails getUrlDetailsById(Integer urlId) ;
	public UrlDetails saveUrlDetails(UrlDetails urlDetails) ;
	public String removeUrlDetails(Integer urlId) ;
}
