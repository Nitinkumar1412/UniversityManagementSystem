package com.University.model;

import com.University.enums.Role;

public class Professor extends user 
{
	private String department;
	private String specialization;
	
	
	public Professor(String name, String email, String password, String department,String specialization) 
	{
		super(name, email, password,Role.PROFESSOR);
		this.department = department;
		this.specialization = specialization;
	}
	

	public String getDepartment() 
	{
		return department;
	}
	
	public void setDepartment(String department) 
	{
		this.department = department;
	}
	
	public String getSpecialization() 
	{
		return specialization;
	}
	
	public void setSpecialization(String specialization) 
	{
		this.specialization = specialization;
	}
	
	
}
