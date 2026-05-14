package com.University.service;

import java.security.InvalidAlgorithmParameterException;

import com.University.databaseAccess.UserDAO;
import com.University.exception.InvalidCredentialsException;
import com.University.exception.userAlreadyExistException;
import com.University.model.user;
import com.University.util.sessionManager;

// this service handle the login and the register in the system
//Service (AuthService) → applies logic

public class AuthenticationService 
{
	private UserDAO userdao  = new UserDAO();
	public void register(user usr)
	{
		
		try
		{
			user existingUser = userdao.fetchUserByUserMail(usr.getEmail());
		
			if(existingUser != null)
			{
				throw new userAlreadyExistException("user already exist please login");
			}
		}
		catch(userAlreadyExistException ex)
		{
			ex.printStackTrace();
		}
		
 
//		usr.setPassword(usr.getPassword());
		
//		inserting the new user now 
		
		userdao.insertUser(usr);
	}
	
	public user login(String email, String password)
	{
		user existingUser =null;
		try
		{
			 existingUser = userdao.fetchUserByUserMail(email);
			
			if(existingUser == null)
			{
				throw new InvalidCredentialsException("INVALID EMAIL/PASSWORD!");
				
			}
			
			if((existingUser.getPassword()).equals(password))
			{
				sessionManager.setCurrentUser(existingUser);
			}
			else
			{
				existingUser = null;
				System.out.print("your password/email is incorrect\n");
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
		return existingUser;
	}
}
