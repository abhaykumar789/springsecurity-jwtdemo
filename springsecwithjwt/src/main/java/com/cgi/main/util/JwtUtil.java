package com.cgi.main.util;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
	private String secret = "raju";

	// 1. Write the method which is used to
	// generate the token based on subject/request

	public String generateToken(String subject) {
		return Jwts.builder().setSubject(subject)
				.setIssuedAt(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(20)))
				.signWith(SignatureAlgorithm.HS512, this.secret.getBytes()).compact();

	}

	// 2. Reading the Claims
	public Claims getClaims(String token) {
		return Jwts.parser()
				.setSigningKey(secret.getBytes())
				.parseClaimsJws(token)
				.getBody();
	}
	
	//3. Exp Date
	public Date getExpDate(String token) {
		      return getClaims(token).getExpiration();
	}
	
	//4. Read Subject/ UserName
	public String getUserName(String token) {
		   return getClaims(token).getSubject();
	}

	//5. Validate Exp Date
	public boolean isTokenExp(String token) {
		  return getExpDate(token).before(new Date(System.currentTimeMillis()));
	}
	
	//6. Validate the Username in token and  database, with expDate
	public boolean validateToken(String token,String username) {
		 String tokenUserName=getUserName(token);
		 return (username.equals(tokenUserName) &&  !isTokenExp(token));
	}
}
