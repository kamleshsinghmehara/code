package com.app.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Utility class for JWT token operations 
 * such as extract user details from token, create or validate token
 * @author Kamlesh
 *
 */
@Service
public class JwtUtil {

	private String secret = "javatechie";

	/**
	 * Method to extract username from JWT Token
	 * @param token
	 * @return username
	 */
	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	/**
	 * Method to extract expiration date from JWT Token
	 * @param token
	 * @return expiration date
	 */
	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	/**
	 * Method to extract all claims typically user authority from JWT Token and apply them
	 * @param <T>
	 * @param token
	 * @param claimsResolver
	 * @return
	 */
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	/**
	 * Method to extract all claims typically user authority from JWT Token
	 * @param token
	 * @return Claims
	 */
	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	/**
	 * Method to check if JWT Token is expired
	 * @param token
	 * @return true/false
	 */
	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	/**
	 * Method to get token using username and user role
	 * @param username
	 * @param role
	 * @return JWT Token
	 */
	public String generateToken(String username, String role) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("role", role);
		return createToken(claims, username);
	}

	/**
	 * Method to create token using claims i.e. authority and subject i.e. username
	 * @param claims
	 * @param subject
	 * @return JWT Token
	 */
	private String createToken(Map<String, Object> claims, String subject) {

		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))// 10 hours
				.signWith(SignatureAlgorithm.HS256, secret).compact();
	}

	/**
	 * Method to validate JWT token using userdetails
	 * @param token
	 * @param userDetails
	 * @return true/false
	 */
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
}
