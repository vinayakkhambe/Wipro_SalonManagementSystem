package com.app.feign;

import org.springframework.beans.factory.annotation.Autowired;

public class HelperFeign {
	
	@Autowired
	static UserClient userclient;
	
	static public Boolean validuserornot(String email)
	{
		try {
		userclient.getuserByEmail( email);
		return true;
		}
		catch (Exception e) {
			return false;
		}
	}

}
