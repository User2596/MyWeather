package com.myweather.user_service.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
	private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	private final long validityMs = 24 * 60 * 60 * 1000; // 24h

	public String generateToken(String subject) {
		Date now = new Date();
		Date exp = new Date(now.getTime() + validityMs);
		return Jwts.builder()
				.setSubject(subject)
				.setIssuedAt(now)
				.setExpiration(exp)
				.signWith(key)
				.compact();
	}

	public String extractSubject(String token) {
		try {
			return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
		} catch (JwtException e) {
			return null;
		}
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
			return true;
		} catch (JwtException e) {
			return false;
		}
	}
}
