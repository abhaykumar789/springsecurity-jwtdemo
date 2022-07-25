package com.cgi.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgi.main.model.Employee;
import com.cgi.main.repository.EmployeeRepository;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;

	public Integer saveEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return this.employeeRepository.save(employee).getId();
	}

	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return this.employeeRepository.findAll();
	}



}
