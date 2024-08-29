package com.app.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.UserDto;
import com.app.dto.UserDto2;
import com.app.entity.User;
import com.app.service.UserService;


@RestController
@RequestMapping("/api/user")
@CrossOrigin("*")
public class UserController {
	
	@Autowired
	UserService uservice;

	@GetMapping("/test")
	public String test()
	{
		return "success test";
	}
	
	@GetMapping("/get/id/{id}")
//	@PreAuthorize("hasRole('ADMIN')")
	public UserDto getuserById(@PathVariable long id)
	{
		UserDto dto = uservice.getUserByid(id);
		return dto;
	}
	
	@GetMapping("/get/all")
	public List<UserDto> getAllUser()
	{
		return uservice.getAllUser();
	}
	
	@PostMapping("/add")
	public UserDto createUser(@RequestBody UserDto2 dto)
	{
	  return uservice.createUser(dto);	
	}
	
	@PutMapping("/update/{id}")
	public UserDto updateUser(@PathVariable long id , @RequestBody UserDto2 dto)
	{
		return uservice.updateUser(id, dto);
	}
	
    @DeleteMapping("/delete/{id}")
	public UserDto deleteUser(@PathVariable long id )
	{
		return uservice.deleteUser(id);
	}
	
	
}
