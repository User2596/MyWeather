package com.myweather.user_service.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {

	@Value("${jwt.expiration}")
	private long jwtExpiration;
	
	@Value("${jwt.secret}")
	private String jwtSecret;
	
	public String generateJwt(String email) {
		Date now = new Date();
		return Jwts.builder()
				.setSubject(email)
				.setIssuedAt(now)
				.setExpiration(new Date(now.getTime() + jwtExpiration))
				.signWith(SignatureAlgorithm.HS256, jwtSecret)
				.compact();
	}
	
}
