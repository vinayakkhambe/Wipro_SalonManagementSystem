package com.app.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.feign.HelperFeign;
import com.app.feign.UserClient;
import com.app.feign.UserDto;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

import java.security.Key;


@Slf4j
@Component
public class jwtutil
{
	final private String secret = "thisisvinayakkhambedemonstrationforsecretkeyinspringsecurityjsonwebtokenauthentication";
	private static final long JWT_TOKEN_VALIDITY = 60*60;
	
	private Key key;

	
	
	@PostConstruct
	public void init() {
		key = Keys.hmacShaKeyFor(secret.getBytes());
	}
	
	

	  public boolean validateToken(final String token) 
	  {
		  try {
				Jws<Claims> clamis = Jwts.parserBuilder().setSigningKey(key).build().
						parseClaimsJws(token);

				System.out.println(clamis.getBody().getSubject());
				
				//if(clamis.getBody().getSubject().equals("kelvin@gmail.com") )
				
				if(HelperFeign.validuserornot( clamis.getBody().getSubject()))
				{
				return true;
				}
				else {
					return false;
				}
				
			  } catch (Exception e) {
				log.error("Invalid JWT : " + e.getMessage());
				throw new RuntimeException("Invalid JWT : ");
			}

		  }


	   
}

