package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.JwtToken;
import com.example.demo.model.LoginDetails;
import com.example.demo.service.AdminService;
import com.example.demo.service.JwtUtil;
import com.example.demo.service.MyUserDetailsService;
import io.jsonwebtoken.Claims;

@RestController
public class HelloController {

	@Autowired
	MyUserDetailsService userDetailsService;

	@Autowired
	JwtUtil jwtService;

	@Autowired
	AdminService adminService;

	@GetMapping(value = "/home")
	public String home() {
		return "HOME end point";
	}

	@GetMapping(value = "/user")
	public String user() {
		return "USER end point";
	}

	@GetMapping(value = "/empInfo/{empId}")
	public String employeeDetails(@PathVariable("empId") int empID) {
		return adminService.getEmployeeInfo(empID);
	}

	@PostMapping(value = "/getJwtToken")
	public String login(@RequestBody LoginDetails loginDetails) {

		UserDetails user = userDetailsService.loadUserByUsername(loginDetails.getUsername());
		Map<String, Object> map = new HashMap<>();
		map.put("username", user.getUsername());
		map.put("authorities", user.getAuthorities());
		return jwtService.createJwtToken(map);

	}

	@PostMapping(value = "/extractJwt")
	public String getJWTClaims(@RequestBody JwtToken jwt) {
		Claims claims = jwtService.extractAllClaims(jwt.getJwtToken());
		return claims.toString();
	}

}
