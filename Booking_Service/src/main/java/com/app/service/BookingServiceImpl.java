package com.app.service;

import java.time.LocalDate;
import java.util.*;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.BookingResponseDto;
import com.app.dto.ServiceDto;
import com.app.dto.StaffDto;
import com.app.dto.UserDto;
import com.app.dto.UserResponseDto;
import com.app.entity.Booking;
import com.app.entity.BookingStatus;
import com.app.entity.PaymentStatus;
import com.app.entity.Services;
import com.app.entity.Staff;
import com.app.entity.User;
import com.app.feign.ServicesClient;
import com.app.feign.StaffClient;
import com.app.feign.UserClient;
import com.app.repository.BookingRepository;
import com.app.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

	@Autowired
	UserClient userClient;

	@Autowired
	ServicesClient serviceClient;

	@Autowired
	StaffClient staffClient;

	@Autowired
	BookingRepository bookRepository;

	@Autowired
	UserRepository uRepository;

	@Autowired
	ModelMapper mapper;

	// get booking by id
	public BookingResponseDto getBookingByID(long id) {
		Booking booked = bookRepository.findById(id).get();
		return mapper.map(booked, BookingResponseDto.class);
	}

	// get all bookings
	public List<BookingResponseDto> getAllBookings() {
		return Arrays.asList(mapper.map(bookRepository.findAll(), BookingResponseDto[].class));
	}

	// Create Booking with userid, staffid , serviceid
	public Booking createBooking(long userid, long serviceid) {
		UserDto udto = userClient.getuserById(userid);
		// StaffDto staffdto = staffClient.getStaffById(staffid);
		ServiceDto servicedto = serviceClient.getServiceById(serviceid);

		User u = mapper.map(udto, User.class);
		// Staff s = mapper.map(staffdto, Staff.class);
		Services service = mapper.map(servicedto, Services.class);

		Booking b = new Booking();
		b.setDate(LocalDate.now());
		// b.setStaff(s);
		b.addServiceToBooking(service);
		b.setBooking_status(BookingStatus.PENDING);

		List<Services> list = b.getServiceList();
		double totalCost = 0.0;

		for (Services services : list) {
			totalCost += services.getPrice();
		}

		b.setPrice(totalCost);

		u.setBooking(b);
		b.setUser(u);

		return bookRepository.save(b);
	}

	// Create Booking with userid, staffid , serviceid
	public Booking createBookingWithListOfService(long userid, List<Long> serviceidlist) {
		UserDto udto = userClient.getuserById(userid);

		List<ServiceDto> serviceDtoList = new ArrayList<ServiceDto>();

		for (Long sid : serviceidlist) {
			ServiceDto sdto = serviceClient.getServiceById(sid);
			serviceDtoList.add(sdto);
		}

		User u = mapper.map(udto, User.class);

		List<Services> serviceList = new ArrayList<>();

		for (ServiceDto dto : serviceDtoList) {

			Services service = new Services();
			service.setName(dto.getName());
			service.setPrice(dto.getPrice());
			service.setServiceClient_id(dto.getId());
			serviceList.add(service);
		}

		Booking b = new Booking();
		b.setDate(LocalDate.now());
		b.setServiceList(serviceList);
		b.setPayment_status(PaymentStatus.PENDING);
		b.setBooking_status(BookingStatus.PENDING);
		List<Services> list = b.getServiceList();
		double totalCost = 0.0;

		for (Services services : list) {
			totalCost += services.getPrice();
		}

		b.setPrice(totalCost);

		u.setBooking(b);
		b.setUser(u);

		return bookRepository.save(b);
	}

	// assign staff to booking only purpose to show user which staff is booked for u

	public BookingResponseDto assignStaffToUserInBooking(long bookingid, long staffid) {

		Booking b = bookRepository.findById(bookingid).get();
		StaffDto dto = staffClient.getStaffById(staffid);
		Staff staff = mapper.map(dto, Staff.class);
		b.setStaff(staff);
		Booking savedBooking = bookRepository.save(b);
		return mapper.map(savedBooking, BookingResponseDto.class);
	}
	
	// remove assigned staff to booking user
	@Override
	public BookingResponseDto removeStaffToUserInBooking(long bookingid) {
		Booking b = bookRepository.findById(bookingid).get();
		b.setStaff(null);
		Booking savedBooking = bookRepository.save(b);
		return mapper.map(savedBooking, BookingResponseDto.class);
	}

	// assign staff and change payment status to booking only purpose to show user
	// which staff is booked for u
	public BookingResponseDto assignStaffWithPayStatusToUserInBooking(long bookingid, long staffid,
			String paymentstatus) {

		Booking b = bookRepository.findById(bookingid).get();
		StaffDto dto = staffClient.getStaffById(staffid);
		Staff staff = mapper.map(dto, Staff.class);
		b.setStaff(staff);
		if (paymentstatus.equals("COMPLETED")) {
			b.setPayment_status(PaymentStatus.COMPLETED);
		} else if (paymentstatus.equals("CANCELLED")) {
			b.setPayment_status(PaymentStatus.CANCELLED);
		} else {
			b.setPayment_status(PaymentStatus.PENDING);
		}

		Booking savedBooking = bookRepository.save(b);
		return mapper.map(savedBooking, BookingResponseDto.class);
	}

	//Assign staff paymentstatus  bookingStatus to booking 
	@Override
	public BookingResponseDto assignStaffPaymentBookingStatusToBooking(long bookingid, long staffid,
			String paymentstatus, String bookstatus) {

		Booking b = bookRepository.findById(bookingid).get();
		StaffDto dto = staffClient.getStaffById(staffid);
		Staff staff = mapper.map(dto, Staff.class);
		b.setStaff(staff);
		if (paymentstatus.equals("COMPLETED")) {
			b.setPayment_status(PaymentStatus.COMPLETED);
		} else if (paymentstatus.equals("CANCELLED")) {
			b.setPayment_status(PaymentStatus.CANCELLED);
		} else {
			b.setPayment_status(PaymentStatus.PENDING);
		}
		if (bookstatus.equals("CONFIRMED")) {
			b.setBooking_status(BookingStatus.CONFIRMED);
		} else if (bookstatus.equals("CANCELLED")) {
			b.setBooking_status(BookingStatus.CANCELLED);
		} else {
			b.setBooking_status(BookingStatus.PENDING);
		}

		Booking savedBooking = bookRepository.save(b);
		return mapper.map(savedBooking, BookingResponseDto.class);

	}
	
	//Assign only paymentstatus and booking status to booking without staff
	@Override
	public BookingResponseDto assignPaymentBookingStatusToBooking(long bookingid, String paymentstatus,
			String bookstatus) {
		
		Booking b = bookRepository.findById(bookingid).get();
	
		if (paymentstatus.equals("COMPLETED")) {
			b.setPayment_status(PaymentStatus.COMPLETED);
		} else if (paymentstatus.equals("CANCELLED")) {
			b.setPayment_status(PaymentStatus.CANCELLED);
		} else {
			b.setPayment_status(PaymentStatus.PENDING);
		}
		if (bookstatus.equals("CONFIRMED")) {
			b.setBooking_status(BookingStatus.CONFIRMED);
		} else if (bookstatus.equals("CANCELLED")) {
			b.setBooking_status(BookingStatus.CANCELLED);
		} else {
			b.setBooking_status(BookingStatus.PENDING);
		}

		Booking savedBooking = bookRepository.save(b);
		return mapper.map(savedBooking, BookingResponseDto.class);
	}

	public BookingResponseDto setBookingStatusToBooking(long bookid, String bookingstatus) {
		Booking persistBooking = bookRepository.findById(bookid).get();
		if (bookingstatus.equals("CONFIRMED")) {
			persistBooking.setBooking_status(BookingStatus.CONFIRMED);
		} else if (bookingstatus.equals("CANCELLED")) {
			persistBooking.setBooking_status(BookingStatus.CANCELLED);
		} else {
			persistBooking.setBooking_status(BookingStatus.PENDING);
		}

		Booking updatedBooking = bookRepository.save(persistBooking);
		return mapper.map(updatedBooking, BookingResponseDto.class);

	}

	public BookingResponseDto findBookingByUserId(long userId) {
		User u = uRepository.findById(userId).get();
		Booking b = u.getBooking();
		return mapper.map(b, BookingResponseDto.class);
	}

	@Override
	public List<UserResponseDto> getAllUsersInBookingService() {
	  List<User> ulist =  uRepository.findAll();
	  List<UserResponseDto> dtolist = new ArrayList<>();
	  
	  for (User u : ulist)
	  {
		UserResponseDto dto = new UserResponseDto();
		dto.setAddress("NO ADDRESS");
		dto.setName(u.getName());
		dto.setBooking_id(u.getBooking());
		dto.setEmail(u.getEmail());
		dto.setId(u.getId());
		dto.setMobile_no(u.getMobile_no());
		
		dtolist.add(dto);
	 }
		return dtolist;
	}

	

	

}
