package com.app.dto;

import java.time.LocalDate;
import java.util.List;

import com.app.entity.Services;
import com.app.entity.Staff;
import com.app.entity.BookingStatus;
import com.app.entity.PaymentStatus;
import com.app.entity.User;

import lombok.Data;


@Data
public class BookingResponseDto {
	
	long id;
	LocalDate date;
	User user;
	List<Services> serviceList ;
	Staff staff;
	Double price;
	PaymentStatus payment_status;
	BookingStatus booking_status;

}
