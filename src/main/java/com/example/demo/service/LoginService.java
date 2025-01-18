package com.example.demo.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import com.example.demo.model.UserInfo;

@Service
public class LoginService {

	private Map<String, UserInfo> usersDatabase = new HashMap<>();

	public LoginService() {
		
		UserInfo user1 = new UserInfo();
		user1.setUsername("user1");
		user1.setPassword("password1");
		user1.setAuthorities(Arrays.asList(new SimpleGrantedAuthority("ADMIN")));
		user1.setAccountNonExpired(true);
		user1.setAccountNonLocked(true);
		user1.setCredentialsNonExpired(true);
		user1.setEnabled(true);

		UserInfo user2 = new UserInfo();
		user2.setUsername("user2");
		user2.setPassword("password2");
		user2.setAuthorities(Arrays.asList(new SimpleGrantedAuthority("DEV")));
		user2.setAccountNonExpired(true);
		user2.setAccountNonLocked(true);
		user2.setCredentialsNonExpired(true);
		user2.setEnabled(true);
		usersDatabase.put("user1", user1);
		usersDatabase.put("user2", user2);
	}

	public UserInfo getDetailsOfUserFromDB(String username) {
		return usersDatabase.get(username);

	}

}
