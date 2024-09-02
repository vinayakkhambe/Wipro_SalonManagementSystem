package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.app.dto.LoginDto;
import com.app.dto.LoginRequestDto;
import com.app.dto.LoginResponseDto;
import com.app.dto.UserDto;
import com.app.dto.UserDto2;
import com.app.service.AuthService;


import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {
	
	@Autowired
	AuthService authservice;
	
	@GetMapping("/test")
	public String test()
	{
		return "test success";
	}

	
	@PostMapping("/signin")
	public LoginResponseDto login (@Valid @RequestBody LoginRequestDto dto)
	{	
		return authservice.login(dto);
	}
	
	@PostMapping("/signup")
	public UserDto authenticateUser (@Valid @RequestBody UserDto2 dto)
	{
		return authservice.signup(dto);
	}
	
//	
//	@PostMapping("/verify")
//	public UserDto validUser(@RequestBody LoginDto dto)
//	{
//		return authservice.validUser(dto.getEmail(), dto.getPassword());
//	}

}
