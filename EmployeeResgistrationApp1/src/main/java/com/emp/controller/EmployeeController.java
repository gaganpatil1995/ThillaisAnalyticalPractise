package com.emp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.emp.bean.Employee;
import com.emp.exception.EmployeeException;
import com.emp.services.EmployeeServices;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeServices empImp ;
	
	@PostMapping("/employee")
	public ResponseEntity<Employee> registerEmployeeDetails(@RequestBody Employee emp){
		
		return new ResponseEntity<>(empImp.registerEmployee(emp),HttpStatus.ACCEPTED) ;
	}
	
	@GetMapping("allemployee")
	public ResponseEntity<List<Employee>> getAllEmployee()throws EmployeeException{
		
		return new ResponseEntity<>(empImp.getAllEmployee() , HttpStatus.FOUND) ;
	}
	
	@GetMapping("/employee/{employeeId}")
	public ResponseEntity<Employee> getEmployeeDetailById(@PathVariable("employeeId") Integer employeeId)throws EmployeeException{
		
		return new ResponseEntity<>(empImp.getEmployeeDetailById(employeeId) , HttpStatus.OK) ;
	}
	
	@PutMapping("/updateemployee")
	public ResponseEntity<Employee> updateEmployeeDetails(@RequestBody Employee emp)throws EmployeeException{
		
		Employee emp2 = empImp.updateEmployeeDetails(emp) ;
		
		return new ResponseEntity<>(emp2 , HttpStatus.OK) ;
	}
	
	@DeleteMapping("/deleteemployee/{employeeId}")
	public ResponseEntity<String> deleteEmployeeById(@PathVariable("employeeId")  Integer employeeId)throws EmployeeException{
		
		return new ResponseEntity<>(empImp.deleteEmployeeDetails(employeeId) ,HttpStatus.GONE) ;
	}
}
