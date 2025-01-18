package com.example.demo.service;

import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtil {
	String secret_key = "vi_key";

	public String createJwtToken(Map<String, Object> claims) {

		return Jwts.builder().setClaims(claims).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000000 * 5656660 * 60 * 100000))
				.signWith(SignatureAlgorithm.HS256, secret_key).compact();
	}

	public Claims extractAllClaims(String jwtToken) {
		
		System.out.println("JWT token validity:  "
				+ Jwts.parser().setSigningKey(secret_key).parseClaimsJws(jwtToken).getHeader());
		try {

			
			return Jwts.parser().setSigningKey(secret_key).parseClaimsJws(jwtToken).getBody();

		} catch (Exception ex) {

			System.out.println("JWT is invalid");
			return null;

		}
	}

}
