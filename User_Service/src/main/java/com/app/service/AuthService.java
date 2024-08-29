package com.app.service;

import com.app.dto.LoginDto;
import com.app.dto.LoginRequestDto;
import com.app.dto.LoginResponseDto;
import com.app.dto.UserDto;

public interface AuthService {
	
	public  void doAuthenticate(String username, String password);
	public LoginResponseDto login(LoginRequestDto jwtRequest); 
	//mymethod
	 UserDto validUser(String email ,String password);
	
}
