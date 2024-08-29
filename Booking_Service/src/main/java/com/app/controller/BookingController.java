package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.BookingResponseDto;
import com.app.dto.ServiceListDto;
import com.app.dto.UserDto;
import com.app.dto.UserResponseDto;
import com.app.entity.Booking;
import com.app.service.BookingService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/booking")
@CrossOrigin("*")
public class BookingController {
	
	
	
	@Autowired
	BookingService bookingService;
	
//	@GetMapping("/user/{id}")
//	public UserDto getUser(@PathVariable long id)
//	{
//		return userClient.getuserById(id);
//	}
//	
//	@GetMapping("/service/{id}")
//	public ServiceDto getService(@PathVariable long id)
//	{
//		return serviceClient.getServiceById(id);
//	}
//	
//	@GetMapping("/staff/{id}")
//	public StaffDto getStaff(@PathVariable long id)
//	{
//		return staffClient.getStaffById(id);
//	}
	
	@Operation(summary = "create booking")
	@PostMapping("/create/{userid}/{serviceid}")
	public Booking createBooking(@PathVariable long userid,@PathVariable long serviceid)
	{
		return bookingService.createBooking(userid, serviceid);
	}
	
	@Operation(summary = "create booking with list of services")
	@PostMapping("/create/{userid}")
	public Booking createBooking(@PathVariable long userid, @RequestBody ServiceListDto dto)
	{		
		return bookingService.createBookingWithListOfService(userid, dto.getSeriveClient_id());
	}
	
	//get by id
	@Operation(summary = "get booking by booking id")
	@GetMapping("/get/id/{id}")
	public BookingResponseDto getById(@PathVariable long id)
	{
		return bookingService.getBookingByID(id);
	}
	
	//get by userid
	@Operation(summary = "get booking by user id")
	@GetMapping("/get/userid/{userId}")
	public BookingResponseDto findBookingByUserId(@PathVariable long userId)
	{
		return bookingService.findBookingByUserId(userId);
	}
	
	//get all
	@Operation(summary = "get all bookings")
	@GetMapping("/get/all")
	public List<BookingResponseDto> getAllBooking()
	{
		return bookingService.getAllBookings();
	}
	
	// change post to put
	@PutMapping("/assignStaffToBooking/{bookingid}/{staffid}")
	public BookingResponseDto assignStaffToUserInBooking(@PathVariable long bookingid,@PathVariable long staffid)
	{
		return bookingService.assignStaffToUserInBooking(bookingid, staffid);
	}
	
	// change post to put
	@PutMapping("/removeStaffFromBooking/{bookingid}")
	public BookingResponseDto removeStaffToUserInBooking(@PathVariable long bookingid)
	{
		return bookingService.removeStaffToUserInBooking(bookingid);
	}
	
	// change post to put
	@PutMapping("/assignStaffAndPaymentToBooking/{bookingid}/{staffid}/{paymentstatus}")
	public BookingResponseDto assignStaffWithPayStatusToUserInBooking(@PathVariable long bookingid,@PathVariable long staffid ,@PathVariable String paymentstatus)
	{
		return bookingService.assignStaffWithPayStatusToUserInBooking(bookingid, staffid, paymentstatus);
	}
	
	// change post to put
	@PutMapping("/assignStaffPaymentBookingStatusToBooking/{bookingid}/{staffid}/{paymentstatus}/{bookstatus}")
	public BookingResponseDto assignStaffPaymentBookingStatusToBooking(@PathVariable long bookingid,@PathVariable long staffid ,@PathVariable String paymentstatus,@PathVariable String bookstatus)
	{
		return bookingService.assignStaffPaymentBookingStatusToBooking(bookingid, staffid, paymentstatus, bookstatus);
	}
	
	// change post to put
	@PutMapping("/assignPaymentBookingStatusToBooking/{bookingid}/{paymentstatus}/{bookstatus}")
	public BookingResponseDto assignPaymentBookingStatusToBooking(@PathVariable long bookingid,@PathVariable String paymentstatus,@PathVariable String bookstatus)
	{
		return bookingService.assignPaymentBookingStatusToBooking(bookingid, paymentstatus, bookstatus);
	}
	
	// change post to put
	@PutMapping("/update/bookingstatus/{bookid}/{bookingstatus}")
	public BookingResponseDto setBookingStatusToBooking (@PathVariable long bookid,@PathVariable  String bookingstatus)
	{
		return bookingService.setBookingStatusToBooking(bookid, bookingstatus);
	}
	
	@GetMapping("/all/customers")
	public List<UserResponseDto> getAllUsersInBookingService()
	{
		return bookingService.getAllUsersInBookingService();
	}
}
