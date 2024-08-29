package com.app.service;

import java.util.List;

import com.app.dto.ServicesDto;
import com.app.dto.ServicesIdListDto;
import com.app.dto.StaffRequestDto;
import com.app.dto.StaffResponseDto;
import com.app.dto.UserDto;
import com.app.entity.Staff;

public interface StaffService {
	//test usercliient
//	public ServicesDto testUser(long id);
	
		//get staff by id
		public StaffResponseDto getStaffById(long staffId);
		//get all staff
		public List<Staff> getAllStaff();
		//create staff
		public StaffResponseDto createStaffMember(StaffRequestDto dto);
		//update staff 
		public StaffResponseDto updateStaffMember(long id ,StaffRequestDto dto);
		//delete staff
		public StaffResponseDto deleteStaffMember(long id );
		//assign user to staff
		public void assignUserToStaff(long staffid, long userid);
		
		public void removeUserFromStaff(long staffid);
		
		//controller method assignServiceToStaff
		public String addServiceToExistingUser(long staffId , long serviceId);
		
		public String removeServiceFromExistingUser(long staffId , long serviceID);
		public String removeAllServicesFromStaff(long staffId );
		public void assignUserandOneServiceToStaff(long staffid,long serviceid , long userid);
		public String assignUserAndAllServicesOfUserToStaff(long staffid,long userid, ServicesIdListDto serviceListDto);
		
		

}
