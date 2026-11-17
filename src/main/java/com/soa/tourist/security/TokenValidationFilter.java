package com.soa.tourist.security;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
public class TokenValidationFilter extends OncePerRequestFilter {
	private final JwtTokenValidator validator;

	public TokenValidationFilter(JwtTokenValidator validator) {
		this.validator = validator;
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest req) {
		return !(HttpMethod.PUT.matches(req.getMethod()) && req.getServletPath().matches("/api/tourists/\\d+"));
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws ServletException, IOException {
		String token = req.getHeader("Token");
		try {
			if (token == null || token.isBlank() || !validator.isValid(token.trim())) {
				writeError(res);
				return;
			}
		} catch (JwtException | IllegalArgumentException ex) {
			writeError(res);
			return;
		}
		chain.doFilter(req, res);
	}

	private void writeError(HttpServletResponse res) throws IOException {
		res.setStatus(500);
		res.setContentType("application/json");
		res.getWriter().write("{\"status\":500,\"error\":\"Internal Server Error\",\"message\":\"Invalid token\"}");
	}
}
