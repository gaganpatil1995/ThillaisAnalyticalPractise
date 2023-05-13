package com.vanghee.entity;

import org.hibernate.annotations.GeneratorType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UrlDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer urlDetailsId ;
	private String urlName ;
	private String url ;

}
