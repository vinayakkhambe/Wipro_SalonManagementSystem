package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.Services;

public interface ServiceRepository extends JpaRepository<Services, Long>{

}
