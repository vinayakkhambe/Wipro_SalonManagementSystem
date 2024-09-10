package com.app.dto;

import com.app.entity.Gender;
import com.app.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
