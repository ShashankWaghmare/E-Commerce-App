package com.example.order_Service.Security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.security.Key;

import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
	
	    private final static String secret="mySuperSecretKeyForJwtTokenThatShouldBeVeryLong"; // use env variable in real apps
//	    private static final Key key = Keys.hmacShaKeyFor(secret.getBytes());
        
	    
	    public Claims extractAllClaims(String token) {
	        return Jwts.parserBuilder()
	                   .setSigningKey(secret.getBytes())
	                   .build()
	                   .parseClaimsJws(token)
	                   .getBody();
	    }

	    public String extractUsername(String token) {
	        return extractAllClaims(token).getSubject();
	    }

	    public boolean isTokenValid(String token) {
	        try {
	            extractAllClaims(token);
	            return true;
	        } catch (Exception e) {
	            return false;
	        }
	    }
	        
	        
	    
}

