package com.app.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Booking extends BaseEntity{
	    
	LocalDate date;
	
	@OneToOne(mappedBy = "booking" ,cascade = CascadeType.ALL)
	@JsonManagedReference
	User user;

	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "booking_id")
	List<Services> serviceList = new ArrayList<>();
	
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "staff_id")
	Staff staff;
	
	Double price;
	
	
	@Enumerated(EnumType.STRING)
	BookingStatus booking_status;

	@Enumerated(EnumType.STRING)
    PaymentStatus payment_status;
	
	
	public void addServiceToBooking(Services s)
	{
		this.serviceList.add(s);
	}
}
