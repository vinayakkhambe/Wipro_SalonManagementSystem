package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.app.dto.LoginDto;
import com.app.dto.LoginRequestDto;
import com.app.dto.LoginResponseDto;
import com.app.dto.UserDto;
import com.app.service.AuthService;
import com.app.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {
	
	@Autowired
	UserService uservice;
	
	@Autowired
	AuthService authservice;
	
	@GetMapping("/test")
	public String test()
	{
		return "test success";
	}

	
	@PostMapping("/signin")
	public LoginResponseDto login ( @RequestBody LoginRequestDto dto)
	{	
		return authservice.login(dto);
	}
	
	
	
	
	@PostMapping("/verify")
	public UserDto validUser(@RequestBody LoginDto dto)
	{
		return authservice.validUser(dto.getEmail(), dto.getPassword());
	}

}
