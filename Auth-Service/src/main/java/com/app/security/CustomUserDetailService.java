package com.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.dto.UserDto;
import com.app.feign.UserClinet;


@Service
public class CustomUserDetailService implements UserDetailsService{

//	@Autowired
//	private UserRepository userrepo;
	
	@Autowired
	private UserClinet userrepo;
	
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{

	//	User u = userrepo.findByEmail (username).orElseThrow(() -> new UsernameNotFoundException(username + "Invalid User email !!!!!"));
		
		if(userrepo.getuserByEmail(username) != null)
		{
		  UserDto u = userrepo.getuserByEmail(username);
		  return new MyUserDetails(u);
		}
		else
		{
			throw new  UsernameNotFoundException(username + "Invalid User email !!!!!");
		}
		
		
	}
	
	
}
