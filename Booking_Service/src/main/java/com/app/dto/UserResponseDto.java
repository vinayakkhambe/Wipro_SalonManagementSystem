package com.app.dto;

import com.app.entity.Booking;

import lombok.Data;

@Data
public class UserResponseDto {

	long id;
	String name;
	String email;
	String address;
	Double mobile_no;
	Booking booking_id;
}
