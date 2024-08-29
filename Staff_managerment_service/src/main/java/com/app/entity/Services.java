package com.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
public class Services extends BaseEntity {
	
	
	   long ServiceClient_id;
	   String servicename;

}
