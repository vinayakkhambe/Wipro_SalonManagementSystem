package com.app.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserDto2 {
	@NotEmpty(message = "empty name")
	String name;

	String email;
	
	String password;
	
	String address;

	Double mobile_no;
	
	Gender gender;
	
	Role role_name;
}
