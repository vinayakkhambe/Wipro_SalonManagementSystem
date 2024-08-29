package com.app.service;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.app.dto.BookingResponseDto;
import com.app.dto.UserDto;
import com.app.dto.UserResponseDto;
import com.app.entity.Booking;

public interface BookingService {

	
	public BookingResponseDto getBookingByID(long id);
	public List<BookingResponseDto> getAllBookings();
	public Booking createBooking(long userid,long serviceid);
	public Booking createBookingWithListOfService(long userid,List <Long> serviceidlist);
	
	public BookingResponseDto assignStaffToUserInBooking(long bookingid, long staffid);
	public BookingResponseDto removeStaffToUserInBooking(long bookingid);
	public BookingResponseDto findBookingByUserId(long userId);
	
	public BookingResponseDto assignStaffWithPayStatusToUserInBooking(long bookingid, long staffid ,String paymentstatus);
	public BookingResponseDto setBookingStatusToBooking (long bookid,String bookingstatus);
	public BookingResponseDto assignStaffPaymentBookingStatusToBooking(long bookingid,long staffid,String paymentstatus,String bookstatus);
	public BookingResponseDto assignPaymentBookingStatusToBooking(long bookingid,String paymentstatus,String bookstatus);
	
	public List<UserResponseDto> getAllUsersInBookingService();
}
