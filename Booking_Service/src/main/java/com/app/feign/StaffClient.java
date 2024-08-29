package com.app.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.app.dto.StaffDto;


//@FeignClient(name ="Staff-Service", url ="http://localhost:8083/")
@FeignClient(name = "STAFF-SERVICE")
public interface StaffClient {

	@GetMapping("/api/staff/get/id/{id}")
	public StaffDto getStaffById(@PathVariable long id);
}
