package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.Staff;

public interface StaffRepository extends JpaRepository<Staff, Long> {

}
