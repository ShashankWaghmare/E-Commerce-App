package com.example.Auth_Service.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.util.Date;
import java.security.Key;
import org.springframework.stereotype.Component;

import lombok.Value;

@Component
public class JwtUtil {
    
	
	
	
	private final String secret="mySuperSecretKeyForJwtTokenThatShouldBeVeryLong";
	
	
	
	private final long expiration=1000 * 60 * 60;
	private final Key key=Keys.hmacShaKeyFor(secret.getBytes());
	
	public String generateToken(String username) {
		return Jwts.builder().setSubject(username).setIssuedAt(new Date()).
				setExpiration(new Date(System.currentTimeMillis() + expiration)).
				signWith(key,SignatureAlgorithm.HS256)
                .compact();
	}
	   public String extractUsername(String token) {
	       return getClaims(token).getSubject();
	   }

	    // Validate token
	    public boolean validateToken(String token, String username) {
	        return (username.equals(extractUsername(token)) && !isTokenExpired(token));
	    }

	    private boolean isTokenExpired(String token) {
	        return getClaims(token).getExpiration().before(new Date());
	    }

	    private Claims getClaims(String token) {
	        return Jwts.parserBuilder()
	                .setSigningKey(key)
	                .build()
	                .parseClaimsJws(token)
	                .getBody();
	    }
	    
	    /*
	    public static void main(String[] args) {
	        JwtUtil jwtUtil = new JwtUtil();
	        String token = jwtUtil.generateToken("shashank");
	        System.out.println("Generated Token: " + token);

	        System.out.println("Username from Token: " + jwtUtil.extractUsername(token));
	        System.out.println("Is Valid: " + jwtUtil.validateToken(token, "shashank"));
	    }
	    */
}
