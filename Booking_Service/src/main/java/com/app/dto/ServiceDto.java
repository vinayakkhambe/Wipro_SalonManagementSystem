package com.app.dto;

import lombok.Data;

@Data
public class ServiceDto {
	
	long id;
	
	String name;
	
	String details;
	
	Double price;
	
	Type type;
}
