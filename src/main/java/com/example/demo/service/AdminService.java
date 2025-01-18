package com.example.demo.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String getEmployeeInfo(int empId) {
		return "Employee Information for empId :"+empId;
		
	}

}
