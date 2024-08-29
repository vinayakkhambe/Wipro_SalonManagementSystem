package com.app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class User {
	
	@Id
	long id;
	String name;
	String email;
	Double mobile_no;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "booking_id")
	@JsonBackReference
	Booking booking;
}
