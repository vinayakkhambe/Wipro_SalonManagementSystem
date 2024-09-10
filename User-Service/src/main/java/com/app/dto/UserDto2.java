package com.app.dto;

import com.app.entity.Gender;
import com.app.entity.Role;

import lombok.Data;

@Data
public class UserDto2 {
	String name;
	String email;
	String password;
	String address;
	Double mobile_no;
	Gender gender;
	Role role_name;
}
