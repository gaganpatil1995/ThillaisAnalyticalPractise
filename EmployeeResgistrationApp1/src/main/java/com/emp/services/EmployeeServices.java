package com.emp.services;

import java.util.List;

import com.emp.bean.Employee;
import com.emp.exception.EmployeeException;

public interface EmployeeServices {

	public Employee getEmployeeDetailById(Integer employeeId)throws EmployeeException ;
	
	public Employee updateEmployeeDetails(Employee emp1)throws EmployeeException ;
	
	public String deleteEmployeeDetails(Integer employeeId)throws EmployeeException ;
	
	public Employee registerEmployee(Employee emp) ;
	
	public List<Employee> getAllEmployee()throws EmployeeException ;
	
	
}
