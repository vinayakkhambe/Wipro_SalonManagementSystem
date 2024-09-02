package com.app.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequestDto {

	@NotNull(message = "Enter valid username/email")
	private String email;	
	@NotNull(message = "Enter valid password")
	private String password;
	
}	