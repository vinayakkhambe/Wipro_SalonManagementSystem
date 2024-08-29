package com.app.dto;

import com.app.entity.Gender;

import lombok.Data;


@Data
public class StaffRequestDto {
  
	String name;
	String skills;
	Gender gender;
	Boolean availability;
	
}
