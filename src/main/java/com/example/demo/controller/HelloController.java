package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.configuration.MyUSerDetails;
import com.example.demo.configuration.MyUserDetailsService;
import com.example.demo.model.JwtToken;
import com.example.demo.model.LoginDetails;
import com.example.demo.model.LoginResponse;
import com.example.demo.service.JwtUtil;

import io.jsonwebtoken.Claims;

@RestController
public class HelloController {

	@Autowired
	MyUserDetailsService userDetailsService;

	@Autowired
	JwtUtil jwtService;

	@RequestMapping(value = "/home")
	public String home() {
		return "HOME end point";
	}

	@RequestMapping(value = "/user")
	public String user() {
		return "USER end point";
	}

	@GetMapping(value = "/admin")
	public String admin() {
		return "ADMIN end point";
	}

	@PostMapping(value = "/getJwtToken")
	public String login(@RequestBody LoginDetails loginDetails) {
		System.out.println("USERNAME ---- :" + loginDetails.getUsername());
		System.out.println("PASSWORD ---- :" + loginDetails.getPassword());
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
