package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ServicesIdListDto;
import com.app.service.StaffService;
import com.app.service.StaffServiceImpl;

import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping("/api/staff/task")
@CrossOrigin("*")
public class StaffTaskController {
	
	@Autowired
	StaffService staffService;
	
	@GetMapping("/test")
	public String test()
	{
		return "test";
	}
	
	//zzz*Remove assigned User from Staff
	@PutMapping("/remove/userfromstaff/{staffid}")
	public String removeUserToStaff( @PathVariable long staffid )
	{
	   staffService.removeUserFromStaff(staffid);
	   return "success";
	}
	
	//zzz*remove all services assign to staff member
	@PutMapping("/remove/allservicesfromstaff/{staffId}")
		public String addServiceToExistingUser(@PathVariable long staffId)
		{
			staffService.removeAllServicesFromStaff(staffId);
			   return "success";   
		}
	//zzz*	remove user and all services from staff
	@PutMapping("/remove/assigneduserandallservicesfromstaff/{staffId}")
		public String removeUserAndAllServicesFromStaff(@PathVariable long staffId)
		{
			staffService.removeUserFromStaff(staffId);
			staffService.removeAllServicesFromStaff(staffId);
			return "success";
		}
	
	
	//zzz*remove 1 service from staff
	@PutMapping("/remove/servicefromstaff/{staffId}/{serviceId}")
	public String removeServiceToExistingUser(@PathVariable long staffId ,@PathVariable long serviceId)
	{
		staffService.removeServiceFromExistingUser(staffId, serviceId);
		   return "success";   
	}

	
	
	//zzz*Assign 1service and 1user to staff at time
	@PutMapping("/assignUserAndOneServiceToStaff/{uid}/{staffid}/{serviceid}")
	public String AssignUserAndOneServiceToStaff(@PathVariable long uid,@PathVariable long staffid,@PathVariable long serviceid)
	{
		staffService.assignUserandOneServiceToStaff(staffid, serviceid, uid);
		return "success";
	}
	
	//zzz*Assign 1 user and list of services belonging to user
	@Operation(summary =  "Assign user and list of services belonging to user")
	@PutMapping("/assignuserandallservices/{staffid}/{userid}")
	public String assignUserAndAllServicesOfUserToStaff(@PathVariable long staffid,@PathVariable long userid,@RequestBody ServicesIdListDto serviceListDto)
	{
		System.out.println("staffid" + staffid + "userid" + userid + "body" + serviceListDto);
		System.out.println("////////////////////////////////////////////////////////////////////////////////////");
		return staffService.assignUserAndAllServicesOfUserToStaff(staffid, userid, serviceListDto);
	}
	
	// zzz* assign 1 service to Staff
		@PutMapping("/assign/servicetostaff/{staffId}/{serviceId}")
		public String addServiceToExistingUser(@PathVariable long staffId ,@PathVariable long serviceId)
		{
			staffService.addServiceToExistingUser(staffId, serviceId);
			   return "success";   
		}
	
		//Assign User to Existing Staff
		@PutMapping("/assign/usertostaff/{uid}/{staffid}")
		public String AssignUserToStaff(@PathVariable long uid , @PathVariable long staffid )
		{
		   staffService.assignUserToStaff(staffid, uid);
		   return "success";
		}
		


}
