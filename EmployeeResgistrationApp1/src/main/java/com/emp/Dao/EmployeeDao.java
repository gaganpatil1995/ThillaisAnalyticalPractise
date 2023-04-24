package com.emp.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emp.bean.Employee;

@Repository
public interface EmployeeDao extends JpaRepository<Employee, Integer>{

}
