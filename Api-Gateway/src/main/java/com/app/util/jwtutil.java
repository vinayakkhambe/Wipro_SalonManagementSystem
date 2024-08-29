package com.app.util;

import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

import java.security.Key;


@Slf4j
@Component
public class jwtutil
{
	final private String secret = "thisisacodingninjasdemonstrationforsecretkeyinspringsecurityjsonwebtokenauthentication";
	private static final long JWT_TOKEN_VALIDITY = 60*60;
	
	private Key key;
	
	@PostConstruct
	public void init() {
		key = Keys.hmacShaKeyFor(secret.getBytes());
	}
	
	

	  public boolean validateToken(final String token) 
	  {
		  try {
				Jwts.parserBuilder().setSigningKey(key).build().
						parseClaimsJws(token);
				return true;
			  } catch (Exception e) {
				log.error("Invalid JWT : " + e.getMessage());
			}

			return false;
		  }


	   
}

