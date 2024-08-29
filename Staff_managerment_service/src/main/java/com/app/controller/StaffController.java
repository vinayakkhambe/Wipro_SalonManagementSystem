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

import com.app.dto.ServicesDto;
import com.app.dto.StaffRequestDto;
import com.app.dto.StaffResponseDto;
import com.app.dto.UserDto;
import com.app.entity.Staff;
import com.app.service.StaffService;
import com.app.service.StaffServiceImpl;


@RestController
@RequestMapping("/api/staff")
@CrossOrigin("*")
public class StaffController {
//	
	@Autowired
	StaffService staffService;
	
//	@GetMapping("/test/serviceclient/{id}")
//	public ServicesDto testUserClient(@PathVariable long id)
//	{
//		return staffService.testUser(id);
//	}
	
	//get staff by id
   @GetMapping("/get/id/{id}")
   public StaffResponseDto getStaffById(@PathVariable long id)
   {
	   return staffService.getStaffById(id);
   }

	// get all staff
	@GetMapping("/get/all")
	public List<Staff> getAllStaff()
	{
		return staffService.getAllStaff();
	}
	
	@PostMapping("/create")
	public StaffResponseDto createStaffMember(@RequestBody StaffRequestDto requestDto)
	{
	   return staffService.createStaffMember(requestDto);
	}
	
	@PutMapping("/update/{id}")
	public StaffResponseDto updateStaffMember(@PathVariable long id , @RequestBody StaffRequestDto requestDto)
	{
	   return staffService.updateStaffMember(id, requestDto);
	}
	
	@DeleteMapping("/delete/{id}")
	public StaffResponseDto updateStaffMember(@PathVariable long id)
	{
	   return staffService.deleteStaffMember(id);
	}
	

}
