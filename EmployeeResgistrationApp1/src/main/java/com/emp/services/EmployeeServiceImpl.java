package com.emp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emp.Dao.EmployeeDao;
import com.emp.bean.Employee;
import com.emp.exception.EmployeeException;

@Service
public class EmployeeServiceImpl implements EmployeeServices {

	@Autowired
	EmployeeDao empDao ;
	
	@Override
	public Employee getEmployeeDetailById(Integer employeeId) throws EmployeeException {
		Optional<Employee> opt1 = empDao.findById(employeeId) ;
		
		if(opt1.isPresent()) {
			return opt1.get() ;
		}
		else {
			throw new EmployeeException("Please Enter Valid Employee ID") ;
		}
		
	}

	@Override
	public Employee updateEmployeeDetails(Employee emp1) throws EmployeeException {
		
		Integer employeeId = emp1.getEmployeeId() ;
		
		if(employeeId == null) {
			throw new EmployeeException("Please Enter Id") ;
		}
		Optional<Employee> opt = empDao.findById(employeeId) ;
		
		if(opt.isPresent()) {
			return empDao.save(emp1) ;
		}else
			throw new EmployeeException("Employee with given Id is not valid") ;
		
		
	}

	@Override
	public String deleteEmployeeDetails(Integer employeeId) throws EmployeeException {
		Optional<Employee> opt = empDao.findById(employeeId) ;
		
		if(opt.isPresent()) {
			empDao.delete(opt.get());
			return "Given Employee is successfully Deleted "+" "+opt.get() ;
		}else
			throw new EmployeeException("Employee with given Id is not present") ;
		
	}

	@Override
	public Employee registerEmployee(Employee emp)  {
		
		return empDao.save(emp);
	}

	@Override
	public List<Employee> getAllEmployee() throws EmployeeException {
		
		List<Employee> empList = empDao.findAll();
		
		if(empList.isEmpty()) {
			throw new EmployeeException("No Employee present in the List") ;
		}
		return empList;
	}

}
