package com.app.dto;

import lombok.Data;

@Data
public class ServicesDto {
	long id;
	String name;
	
	String details;
	
	Double price;
	
	String type;
}