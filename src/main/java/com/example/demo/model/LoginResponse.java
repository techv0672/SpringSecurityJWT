package com.example.demo.model;

public class LoginResponse {
	
	private String username;
	private String jwtString;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getJwtString() {
		return jwtString;
	}
	public void setJwtString(String jwtString) {
		this.jwtString = jwtString;
	}
	

}
