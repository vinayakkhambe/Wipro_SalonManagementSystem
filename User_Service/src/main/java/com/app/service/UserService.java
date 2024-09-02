package com.app.service;

import java.util.List;

import com.app.dto.UserDto;
import com.app.dto.UserDto2;
import com.app.entity.User;

public interface UserService {
	
	 UserDto validUser(String email ,String password);
	
	 UserDto getUserByid(long id);
	 
	 UserDto getUserByEmail(String email);
	 
	 List<UserDto> getAllUser();
	 
	 UserDto createUser(UserDto2 dto);
	 
	 UserDto updateUser(long id ,UserDto2 dto);
	 
	 UserDto deleteUser (long id);
	

}
