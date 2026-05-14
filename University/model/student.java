package com.University.model;
import com.University.enums.Role;


public class student extends user   // student is->a user (inheretence)
{
	private int currentCredits;
	private int semester;
	
//	constructor
	
	public student(String name, String email, String password, int currentCredits,int semester) 
	{
		super(name, email, password, Role.STUDENT);
		this.currentCredits = currentCredits;
		this.semester = semester;
	}
	
	
	public student(String name, String email, String password, String role) 
	{
		// TODO Auto-generated constructor stub
		super(name,email,password,Role.STUDENT);
	}

	public int getCurrentCredits() 
	{
		return currentCredits;
	}

	public void setCurrentCredits(int currentCredits) 
	{
		this.currentCredits = currentCredits;
	}

	public int getSemester() 
	{
		return semester;
	}

	public void setSemester(int semester) 
	{
		this.semester = semester;
	}
	

	

	
	
}
