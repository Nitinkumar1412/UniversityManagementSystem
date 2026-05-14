package com.University.util;

import com.University.model.user;

public class sessionManager 
{
	private static user currentUser;
	
//	logged in user info
	
	public static void setCurrentUser(user usr)
	{
		currentUser = usr;
	}

// get logged in user
	public static user getCurrentUser() 
	{
		return currentUser;
	}
	
//	logout or to clear session
	public static void logout()
	{
		currentUser = null;
	}
	
	
}

/* by this hm kabhi bhi che toh apne logged in user ki information 
nikal skte hai bs ek command likh kr---
User user = SessionManager.getCurrentUser(); 
We make it static so it can be shared across 
the entire application without creating objects
Static = shared/global access
)

 Static = one shared session for whole app
 Non-static = separate copies (wrong for session)
*/