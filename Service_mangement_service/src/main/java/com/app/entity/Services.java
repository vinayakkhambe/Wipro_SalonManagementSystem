package com.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Services extends BaseEntity{
	
	String name;
	
	String details;
	
	Double price;
	
	@Enumerated(EnumType.STRING)
	Type type;

}
