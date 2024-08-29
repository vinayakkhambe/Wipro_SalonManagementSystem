package com.app.dto;

import com.app.entity.Type;

import lombok.Data;

@Data
public class ServicesResponseDto {
	long id;
	
	String name;
	
	String details;
	
	Double price;
	
	Type type;
}
