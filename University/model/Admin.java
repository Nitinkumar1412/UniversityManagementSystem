package com.University.model;

import com.University.enums.Role;

public class Admin extends user 
{

	public Admin(String name, String email, String password) 
	{
		super(name, email, password ,Role.ADMIN);
	}
	
}
