package com.University.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// from here we get the connection to the database

public class DBConnection 
{
	 public static Connection getConnection() 
	 {
		 	String url = "jdbc:mysql://localhost:3306/university_db";
            String user = "root";
            String password = "nitin"; 
	        Connection conn = null;
	        
	        
	        try 
	        {    
	            conn = DriverManager.getConnection(url, user, password);
	        }
	        catch (Exception ex) 
	        {
	            ex.printStackTrace();
	        }

	        return conn;
	    }
}
