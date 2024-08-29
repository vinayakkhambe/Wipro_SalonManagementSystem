package com.app.entity;

import com.app.dto.Type;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Services extends BaseEntity {

    
	long ServiceClient_id;
	
	String name;
	
	Double price;
	
}
