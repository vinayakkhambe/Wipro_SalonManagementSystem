package com.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User extends BaseEntity {
	
	String name;
	String email;
	String password;
	String address;
	Double mobile_no;
	
	@Enumerated(EnumType.STRING)
	Gender gender;
	
    @Enumerated(EnumType.STRING)
	Role role_name;

}
