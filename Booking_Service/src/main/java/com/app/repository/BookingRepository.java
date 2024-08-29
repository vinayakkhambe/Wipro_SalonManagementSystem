package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.Booking;
import com.app.entity.User;

public interface BookingRepository extends JpaRepository<Booking, Long>{

	Booking findByUser(User u);
}
