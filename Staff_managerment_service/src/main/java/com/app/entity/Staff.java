package com.app.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Staff extends BaseEntity {
	
	String name;
	String skills;
	
	@Enumerated(EnumType.STRING)
	Gender gender;
	
	
	Boolean availability;
	
	@OneToMany(fetch = FetchType.EAGER ,cascade = CascadeType.ALL)
    @JoinColumn(name ="Staff_id" )
	List<Services> servicelist = new ArrayList<Services>();
   	
//    @OneToMany(fetch = FetchType.EAGER ,cascade = CascadeType.ALL)
//    @JoinColumn(name ="Staff_id" )
//	List<User> userlist = new ArrayList<User>();
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	User user;
	

	
//helper methods
	
	//add service as task to staff member
	public void assignServiceToStaff(Services service)
	{
		this.servicelist.add(service);
	}
	
	//remove service as task from staff member
		public void removeServiceFromStaff(Services service)
		{
			this.servicelist.remove(service);
		}
	
  //remove all services assign to staff member
		public void removeAllServicesFromStaff()
		{
			this.servicelist.clear();
		}
	
	//assign user as task to staff member
	public void assignUserToStaff(User u)
	{
		this.user = u;
	}
	
	//remove user as task from staff member
		public void removeUserToStaff()
		{
			this.user = null;
		}
		
	// assign whole list of services to staff
		public void assignWholeListOfServices(List<Services> list)
		{
			this.servicelist = list;
		}

	
}
