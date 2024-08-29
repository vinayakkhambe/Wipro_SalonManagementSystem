package com.app.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.app.dto.UserDto;

//@FeignClient(name ="User-Service", url ="http://localhost:8081/")
@FeignClient(name = "USER-SERVICE")
public interface UserClient 
	{
		@GetMapping("api/user/get/id/{id}")
		public UserDto getuserById(@PathVariable long id);
	
	}