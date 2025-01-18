package com.example.demo.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.MyUSerDetails;
import com.example.demo.model.UserInfo;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	BCryptPasswordEncoder encoder;

	@Autowired
	LoginService loginService;

	@Override
	public MyUSerDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserInfo userInfo = loginService.getDetailsOfUserFromDB(username);
		MyUSerDetails userDetails = new MyUSerDetails();
		userDetails.setUsername(userInfo.getUsername());
		userDetails.setPassword(userInfo.getPassword());
		userDetails.setAuthorities(userInfo.getAuthorities());

		return userDetails;

	}

}
