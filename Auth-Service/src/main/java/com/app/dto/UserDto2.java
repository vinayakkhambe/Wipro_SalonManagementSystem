package com.app.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserDto2 {
	@NotEmpty(message = "empty name")
	String name;
	@Email
	String email;
	@NotEmpty
	String password;
	@NotEmpty
	String address;
	@NotEmpty
	Double mobile_no;
	@NotEmpty
	Gender gender;
	@NotEmpty
	Role role_name;
}
