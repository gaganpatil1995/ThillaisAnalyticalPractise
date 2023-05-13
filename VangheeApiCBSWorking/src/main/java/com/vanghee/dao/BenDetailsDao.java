package com.vanghee.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vanghee.entity.BenDetails;

@Repository
public interface BenDetailsDao extends JpaRepository<BenDetails, Integer>{

	Optional<List<BenDetails>> findByMobile(String mobile);
	Optional<List<BenDetails>> findByAccNo(String accNo);
}
