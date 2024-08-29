package com.app.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.app.dto.ServiceDto;


//@FeignClient(name ="Services-management", url ="http://localhost:8082/")
@FeignClient(name = "SERVICES-MANAGEMENT")
public interface ServicesClient {

	@GetMapping("/api/services/get/id/{id}")
	public ServiceDto getServiceById(@PathVariable long id);
}
