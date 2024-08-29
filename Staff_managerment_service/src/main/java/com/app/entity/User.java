package com.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class User  {
	
   @Id
   long id;
   
   @Column(name = "user_name")
   String name;
   
   @Enumerated(EnumType.STRING)
   Gender gender;

}
