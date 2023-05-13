package com.vanghee.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vanghee.entity.UrlDetails;

@Repository
public interface UrlDetailsRepository extends JpaRepository<UrlDetails, Integer>{
   
	Optional<List<UrlDetails>> findByUrlName(String urlName);
}
