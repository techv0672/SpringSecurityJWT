package com.example.demo.configuration;

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

import com.example.demo.model.UserInfo;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	BCryptPasswordEncoder encoder;

	@Override
	public MyUSerDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Map<String,UserInfo> usersDB=new HashMap<>();

		//System.out.println("Username got: ---->" + username);
		if (username.equalsIgnoreCase("user1")) {

			MyUSerDetails userDetails = new MyUSerDetails();
			userDetails.setUsername("user1");
			userDetails.setPassword(encoder.encode("pwd1"));
			userDetails.setAuthorities(Arrays.asList(new SimpleGrantedAuthority("ADMIN")));
			return userDetails;
		} else if (username.equalsIgnoreCase("user2")) {

			MyUSerDetails userDetails2 = new MyUSerDetails();
			userDetails2.setUsername("user2");
			userDetails2.setPassword(encoder.encode("pwd2"));
			userDetails2.setAuthorities(Arrays.asList(new SimpleGrantedAuthority("DEV")));
			return userDetails2;

		} else {

			return null;
		}
	}

}
