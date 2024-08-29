package com.app.dto;

import com.app.entity.Type;

import lombok.Data;

@Data
public class ServicesRequestDto {
	
	String name;
	
	String details;
	
	Double price;
	
	Type type;
}
