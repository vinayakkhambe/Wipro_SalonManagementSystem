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
import org.springframework.security.crypto.password.PasswordEncoder;
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
	
	@Autowired
	PasswordEncoder passwordEncoder;
	

	
	public  void doAuthenticate(String username, String password) 
	 { 
//		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
//				password);
//		//Authentication verifiedCredentials;
//		try {
//			 manager.authenticate(authenticationToken);
//
//      		} catch (BadCredentialsException e) 
//		     {
//			    throw new BadCredentialsException("Invalid Username or Password in doaunthenticate");
//		     }	
//		
//		System.out.println("all fine");
		
		LoginDto dto = new LoginDto();
		dto.setEmail(username);
		dto.setPassword(password);
		try{
			System.out.println("Inside try????????????????????????????????????????????????????????????????//");
		urepo.validUser( dto);
		}
		catch (Exception e) {
			throw new RuntimeException("Wrong email and password");
		}
	  }
	
	
	@Override
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

//		UserDto2 d = new UserDto2();
//		d.setAddress(dto.getAddress());
//		d.setEmail(dto.getEmail());
//		d.setGender(dto.getGender());
//		d.setMobile_no(dto.getMobile_no());
//		d.setName(dto.getName());
//		d.setRole_name(dto.getRole_name());
//		d.setPassword(passwordEncoder.encode(dto.getPassword()));
		
		UserDto response = urepo.createUser(dto);

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
