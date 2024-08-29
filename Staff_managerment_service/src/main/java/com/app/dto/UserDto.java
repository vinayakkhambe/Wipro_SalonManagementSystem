package com.app.dto;

import com.app.entity.Gender;


import lombok.Data;

@Data
public class UserDto {
	long id;
	String name;
	String email;
	String password;
	String address;
	Double mobile_no;
	Gender gender;
	Role role_name;
}
