package com.example.demo.model;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

public class UserInfo {
	private String username;
	private String password;
	private List<GrantedAuthority> authorities;

}
