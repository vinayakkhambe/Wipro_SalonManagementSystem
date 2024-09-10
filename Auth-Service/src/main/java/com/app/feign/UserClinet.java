package com.app.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.app.dto.LoginDto;
import com.app.dto.UserDto;
import com.app.dto.UserDto2;

@FeignClient(name ="USER-SERVICE")
public interface UserClinet {

		@GetMapping("api/user/get/id/{id}")
		public UserDto getuserById(@PathVariable long id);
		
		@GetMapping("api/user/get/email/{email}")
		public UserDto getuserByEmail(@PathVariable String email);	

		@PostMapping("api/user/add")
		public UserDto createUser(@RequestBody UserDto2 dto);
		
		@PostMapping("/api/user/verify")
		public UserDto validUser(@RequestBody LoginDto dto);
		
		
		

}
