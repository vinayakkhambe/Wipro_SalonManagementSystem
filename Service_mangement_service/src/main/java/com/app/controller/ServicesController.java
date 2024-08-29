package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ServicesRequestDto;
import com.app.dto.ServicesResponseDto;
import com.app.service.ServiceCatelogeService;

@RestController
@RequestMapping("/api/services")
@CrossOrigin("*")
public class ServicesController {
	
	@Autowired
	ServiceCatelogeService sService;

	
	@GetMapping("/get/id/{id}")
	public ServicesResponseDto getServiceById(@PathVariable long id)
	{
		return sService.getServiceById(id);
	}
	
	@GetMapping("/get/all")
	public List<ServicesResponseDto> getAllServices()
	{
		return sService.getAllSevices();
	}
	
	@PostMapping("/add")
	public ServicesResponseDto createService(@RequestBody ServicesRequestDto requestDto)
	{
		return sService.createService(requestDto);
	}
	
	@PutMapping("/update/{id}")
	public ServicesResponseDto updateService(@PathVariable long id,@RequestBody ServicesRequestDto requestDto)
	{
		return sService.updateService(id, requestDto);
	}
	
	@DeleteMapping("/delete/{id}")
	public ServicesResponseDto deleteService(@PathVariable long id)
	{
		return sService.deleteService(id);
	}
	
	
	
	
	
}
