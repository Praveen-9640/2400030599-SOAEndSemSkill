package com.soa.tourist.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;

@Component
public class JwtTokenValidator {
	private final SecretKey key;

	public JwtTokenValidator(@Value("${app.jwt.secret}") String secret) {
		key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
	}

	public boolean isValid(String token) {
		Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
		return true;
	}
}
