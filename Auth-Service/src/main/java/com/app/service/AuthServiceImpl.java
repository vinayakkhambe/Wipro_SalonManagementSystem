package com.app.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.dto.LoginDto;
import com.app.dto.LoginRequestDto;
import com.app.dto.LoginResponseDto;
import com.app.dto.UserDto;
import com.app.dto.UserDto2;
import com.app.feign.UserClinet;
import com.app.jwt.JwtAuthenticationHelper;


@Service
public class AuthServiceImpl implements AuthService {
	
//	@Autowired
//	UserRepository urepo;
	
	@Autowired
	UserClinet urepo;
	
	@Autowired
	ModelMapper mapper;
	
	@Autowired
	AuthenticationManager manager;

	@Autowired
	JwtAuthenticationHelper jwtHelper;

	@Autowired
	UserDetailsService userDetailsService;
	
	public  void doAuthenticate(String username, String password) 
	 { 
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
				password);
		//Authentication verifiedCredentials;
		try {
			 manager.authenticate(authenticationToken);

      		} catch (BadCredentialsException e) 
		     {
			    throw new BadCredentialsException("Invalid Username or Password");
		     }	
		
		System.out.println("all fine");
	  }
	
	
	public LoginResponseDto login(LoginRequestDto jwtRequest) 
	{		
		// authenticate with Authentication manager
		this.doAuthenticate(jwtRequest.getEmail(), jwtRequest.getPassword());
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getEmail());
		String token = jwtHelper.generateToken(userDetails);

		LoginResponseDto response = LoginResponseDto.builder().token(token)
				                                                .email(jwtRequest.getEmail())
				                                                .build();
		return response;
	}
	
	@Override
	public UserDto signup(UserDto2 dto) {

		String userEmail = dto.getEmail();
//		if (urepo.getuserByEmail(userEmail) == null)
//		{
//			throw new RuntimeException("User already Existed");
//		}
		
		BCryptPasswordEncoder bCryptPasswordEncoder= new BCryptPasswordEncoder();
		String encodedPassword = bCryptPasswordEncoder.encode(dto.getPassword());
		dto.setPassword(encodedPassword);
		
		UserDto response = urepo.createUser(dto);
		
//		User u = mapper.map(dto, User.class);
//		u.setPassword(encodedPassword);
//		User saveduder = urepo.save(u);
//		return mapper.map(saveduder, UserDto.class);
		return response;
	}
	


	
	// my method
//	public UserDto validUser(String email ,String password)
//	{
//		User u = urepo.findByEmail(email).get();
//		
//		if(u.getPassword().equals(password))
//		{
//			return mapper.map(u, UserDto.class);
//		}
//		else
//		{
//			throw new IllegalArgumentException("Invalid password.");
//		}
//	}


	


	

}
