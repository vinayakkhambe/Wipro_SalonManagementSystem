package com.app.jwt;



import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


import javax.crypto.spec.SecretKeySpec;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtAuthenticationHelper {
	
	private Key key;
	
	private String secret = "thisisvinayakkhambedemonstrationforsecretkeyinspringsecurityjsonwebtokenauthentication";
	private static final long JWT_TOKEN_VALIDITY = 60*60;
	
	@PostConstruct
	public void init() {
		key = Keys.hmacShaKeyFor(secret.getBytes());
	}

	public String getUserNameFromToken(String token) {
		Claims claims = getClaimsFromToken(token);

		return claims.getSubject(); // getsub is username return
	}

	public Claims getClaimsFromToken(String token) {
		Claims claims = Jwts.parserBuilder().setSigningKey(secret.getBytes()).build().parseClaimsJws(token).getBody();
		return claims;
	}

	public Boolean isTokenExpired(String token) {
		Claims claims = getClaimsFromToken(token);
		Date expDate = claims.getExpiration();
		return expDate.before(new Date());
	}

	public String generateToken(UserDetails userDetails) {

		Map<String, Object> claims = new HashMap<>();

		return Jwts.builder().setClaims(claims).setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(new SecretKeySpec(secret.getBytes(), SignatureAlgorithm.HS512.getJcaName()),
						SignatureAlgorithm.HS512)
				.compact();
	}
	
//	  public boolean validateToken(final String token) {
//		  try {
//				Jwts.parserBuilder().setSigningKey(key).build().
//						parseClaimsJws(token);
//				return true;
//			} catch (Exception e) {
//				log.error("Invalid JWT : " + e.getMessage());
//			}
//
//			return false;
//		  }
	  
	  
	  
}
	  
//	  private Key getSignKey() {
//	        byte[] keyBytes = Decoders.BASE64.decode(secret);
//	        return Keys.hmacShaKeyFor(keyBytes);
//	    }



