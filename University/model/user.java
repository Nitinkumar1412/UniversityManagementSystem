package com.University.model;

import com.University.enums.*;

// User abstract because it represents a general concept, not a real entity we create.

public abstract class user 
{
    private int id;
    private String name;
    private String email;
    private String password;
    private Role role;
    
    
	public user(String name, String email, String password, Role role) 
	{
		 // immutable once fixed cannot be changed
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;    // role is unchangeable
	}
	
	public void setId(int id)
	{
		this.id = id;
	}

	public int getId() 
	{
		return id;
	}


	public String getName() 
	{
		return name;
	}


	public void setName(String name) 
	{
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) 
	{
		this.email = email;
	}


	public String getPassword() 
	{
		return password;
	}


	public void setPassword(String password) 
	{
		this.password = password;
	}
    
	public Role getRole() 
	{
        return role;
    }
	
}
