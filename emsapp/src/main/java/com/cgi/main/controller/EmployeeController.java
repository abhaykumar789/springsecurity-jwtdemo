package com.cgi.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cgi.main.model.Employee;
import com.cgi.main.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/save")
	public ResponseEntity<String> saveEmployee(@RequestBody Employee employee)
	{
		System.out.println(employee+"<---");
		Integer id=this.employeeService.saveEmployee(employee);
		return ResponseEntity.ok("Employee Save Id is:"+id);
	}
	
	@GetMapping("/all")
	public List<Employee> getAllEmployees(){
		
		return this.employeeService.getAllEmployees();
	}
	
	@GetMapping("/msg")
	public ResponseEntity<String> message(){
		return ResponseEntity.ok("WELCOME TO HOME PAGE");
	}
	@GetMapping("/home")
	public  String home() {
		return "home";
	}
}
