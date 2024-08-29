package com.app.dto;

import java.util.List;

import com.app.entity.Services;
import com.app.entity.User;

import lombok.Data;

@Data
public class StaffDto {

	long id;
	String name;
	String skills;
	Gender gender;
	Boolean availability;
	User user;
	List<Services> servicelist;
}
