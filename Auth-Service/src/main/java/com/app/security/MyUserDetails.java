package com.app.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.app.dto.UserDto;


public class MyUserDetails implements UserDetails {
	
	
	private static final long serialVersionUID = 1L;
	private UserDto user;
	
	public MyUserDetails(UserDto user) {
		super();
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	
		String roleName = "ROLE_" + this.user.getRole_name();
		//System.out.println(roleName);
		return Collections.singletonList(new SimpleGrantedAuthority(roleName));
		
//		List<GrantedAuthority> list  = new ArrayList<>();
//		
//		list.add(new SimpleGrantedAuthority(roleName));
//		return list;
		//return Collections.list(new SimpleGrantedAuthority(this.user.getRole_name().toString()));
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getEmail();
	}
	
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;//user a/c is not expired 
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
