package com.app.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.ServicesDto;
import com.app.dto.ServicesIdListDto;
import com.app.dto.StaffRequestDto;
import com.app.dto.StaffResponseDto;
import com.app.dto.UserDto;
import com.app.entity.Services;
import com.app.entity.Staff;
import com.app.entity.User;
import com.app.feign.ServicesClient;
import com.app.feign.UserClient;
import com.app.repository.ServiceRepository;
import com.app.repository.StaffRepository;

@Service
public class StaffServiceImpl implements StaffService {
	
	@Autowired
	StaffRepository staffrepository;
	
	@Autowired
	ServiceRepository servicerepository;
	
	@Autowired
	UserClient userclient;
	
	@Autowired
	ServicesClient servicesCient;
	
	
	@Autowired
	ModelMapper mapper;

//	@Override
//	public ServicesDto testUser(long id)
//	{
//		return servicesCient.getServiceById(id);
//	}
	
	//get staff by id
	public StaffResponseDto getStaffById(long staffId)
	{
		Staff persistStaff = staffrepository.findById(staffId).get();
		StaffResponseDto dto = mapper.map(persistStaff,StaffResponseDto.class);
		return dto;
	}
 
	//get all staff
	public List<Staff> getAllStaff()
	{
		return staffrepository.findAll();
	}
	
	//create staff
	public StaffResponseDto createStaffMember(StaffRequestDto dto)
	{
	  Staff staffToSave = mapper.map(dto, Staff.class);
	 Staff persistStaff = staffrepository.save(staffToSave);
	 return mapper.map(persistStaff, StaffResponseDto.class);
	}
	
	//update staff 
	public StaffResponseDto updateStaffMember(long id ,StaffRequestDto dto)
	{
		Staff persistStaff = staffrepository.findById(id).get();
		persistStaff.setAvailability(dto.getAvailability());
		persistStaff.setGender(dto.getGender());
		persistStaff.setName(dto.getName());
		persistStaff.setSkills(dto.getSkills());
		
		Staff updatedStaff = staffrepository.save(persistStaff);
		return mapper.map(updatedStaff, StaffResponseDto.class);
		
	}
	
	//delete staff
	public StaffResponseDto deleteStaffMember(long id )
	{
		Staff persistStaff = staffrepository.findById(id).get();
		staffrepository.delete(persistStaff);
		return mapper.map(persistStaff, StaffResponseDto.class);
	}
	
// -----------------EXTRA FEATURES----------------------------------------------------------------------------//
	
	
	
	//Assign user to Staff
	
	
	public void assignUserToStaff(long staffid, long userid)
	{
	  Staff staff = staffrepository.findById(staffid).get();
      UserDto udto = userclient.getuserById(userid);
    
      User u = mapper.map(udto, User.class);

      staff.assignUserToStaff(u);
      staffrepository.save(staff);
	}
	
	public void removeUserFromStaff(long staffid)
	{
	  Staff staff = staffrepository.findById(staffid).get();
//      UserDto udto = userclient.getuserById(userid);
//    
//      User u = mapper.map(udto, User.class);

      staff.removeUserToStaff();
      staffrepository.save(staff);
	}
	
	//controller method assignServiceToStaff
	public String addServiceToExistingUser(long staffId , long serviceId)
	{
	   ServicesDto feignDto =servicesCient.getServiceById(serviceId);
	   Services s = new Services();
	   s.setServiceClient_id(feignDto.getId());
	   s.setServicename(feignDto.getName());
	   
	   Staff staff = staffrepository.findById(staffId).get();
	   staff.assignServiceToStaff(s);
	   
	   staffrepository.save(staff);
	   return "done";
	   
	}
	

	public String removeServiceFromExistingUser(long staffId , long serviceID)
	{
	 //   ServicesDto feignDto =servicesCient.getServiceById(serviceClinetId);
//	   Services s = new Services();
//	   s.setServiceClient_id(feignDto.getId());
	   Services s =servicerepository.findById(serviceID).get();
	   
	   Staff staff = staffrepository.findById(staffId).get();
	   staff.removeServiceFromStaff(s);
	   staffrepository.save(staff);
	   return "done";
	}
	
	public String removeAllServicesFromStaff(long staffId )
	{
		 Staff staff = staffrepository.findById(staffId).get();
		 staff.removeAllServicesFromStaff();
		 staffrepository.save(staff);
		   return "done";
	}
	
	public void assignUserandOneServiceToStaff(long staffid,long serviceid , long userid)
	{
		//get staff frm staffservice
	   Staff staff = staffrepository.findById(staffid).get();
	   // get userDto as respone from user service
	  UserDto udto = userclient.getuserById(userid);
	  //get servicedto as response from services service
	  ServicesDto sDto = servicesCient.getServiceById(serviceid);
	  
	  // this is helping entity from staffservice with only id and fk as staffid
	  User u = new User();
	  u.setId(udto.getId());
	  u.setName(udto.getName());
	  u.setGender(udto.getGender());
	  
	  Services s = new Services();
	 // s.setId(sDto.getId());
	  s.setServiceClient_id(sDto.getId());
	  s.setServicename(sDto.getName());
	  
	  staff.setUser(u);
	 staff.assignServiceToStaff(s);
	 staffrepository.save(staff);
	}
	
	public String assignUserAndAllServicesOfUserToStaff(long staffid,long userid, ServicesIdListDto serviceListDto)
	{
		  //get staff frm staffservice
		   Staff staff = staffrepository.findById(staffid).get(); //1
		// get userDto as respone from user service
		   UserDto udto = userclient.getuserById(userid);     //1
		  
		   //list of service ids .. 4 ,5 servcieid
		  List<Long> requestedServiceIdList = serviceListDto.getServicesIdList();
		  
		  // services from services_management in form of dto
		  List<ServicesDto> ServiceClientDtosList = new ArrayList<>(); 
		  
		  for (Long serviceid: requestedServiceIdList) 
		  {
			//get servicedto as response from services service
			  ServicesDto sDto = servicesCient.getServiceById(serviceid);
			  ServiceClientDtosList.add(sDto);
	    	}
		   
			  
		  List<Services> servicesList = new ArrayList<>();		  
			 for (ServicesDto dto : ServiceClientDtosList)
			 {
				 Services s = new Services();
				  s.setServiceClient_id(dto.getId());
				  s.setServicename(dto.getName());
				  servicesList.add(s);
				  
			}
			 
			  // this is helping entity from staffservice with only id and fk as staffid
			  User u = new User();
			  u.setId(udto.getId());
			  u.setName(udto.getName());
			  u.setGender(udto.getGender());
			    
		     staff.setUser(u);
			 staff.assignWholeListOfServices(servicesList);
			 staffrepository.save(staff);
			 return "success";
			}
	

}
