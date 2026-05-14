package com.University.databaseAccess;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.University.enums.Role;
import com.University.model.*;
import com.University.util.DBConnection;

public class UserDAO 
{
//	Connection → Prepare Query → Set Values → Execute → Get ResultSet
	public user fetchUserByUserMail(String usermail)
	{
		user usr = null;
		try
		{
			Connection conn = DBConnection.getConnection();
			String query = "SELECT * FROM users WHERE email = ?";
			
			PreparedStatement stmt = conn.prepareStatement(query);
			
			stmt.setString(1, usermail);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next())
			{
 				int id = rs.getInt("user_id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String password = rs.getString("password");
				Role role = Role.valueOf(rs.getString("role"));
				//	now creating the object based on role
				if(role == Role.STUDENT)
				{
					int credits,semester;
					PreparedStatement ps1 = conn.prepareStatement("Select semester, credits from students where s_id = ?");
					ps1.setInt(1, id);
					ResultSet rs2 = ps1.executeQuery();
					if(rs2.next()) 
					{
						semester = rs2.getInt("semester");
						credits = rs2.getInt("credits");
						usr = new student(name,email,password,credits,semester);
					}
					
					
					
				}
				else if(role == Role.PROFESSOR)
				{
					usr = new Professor(name,email,password,"","");
				}
				else
				{
					usr = new Admin(name, email, password);
				}
				usr.setId(id);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return usr;	
	}
	
//	 INSERT FUNCTION 
	public boolean insertUser(user usr)
	{
		
		try
		{
			Connection conn = DBConnection.getConnection();
			String query = "INSERT INTO users (name, email, password, role) VALUES (?, ?, ?, ?)";
	
			PreparedStatement stmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			
			stmt.setString(1, usr.getName());
			stmt.setString(2, usr.getEmail());
			stmt.setString(3, usr.getPassword());
			stmt.setString(4, usr.getRole().toString());
			
			int rows = stmt.executeUpdate();
			
			ResultSet rs = stmt.getGeneratedKeys();
			if(rs.next()) 
			{
				usr.setId(rs.getInt(1));
			}

			//inserting into the other tables
			if(usr instanceof student)
			{
				insertStudent((student)usr);
			}
			else if(usr instanceof Professor)
			{
				insertProfessor((Professor)usr);
			}
			
			return rows >0;
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}

		
		return false;
	}
	
	
	public void insertStudent(student std) throws Exception
	{
		Connection conn = DBConnection.getConnection();
		
		String query = "INSERT INTO students (s_id, semester, credits) VALUES (?, ?, ?)";
		
		PreparedStatement stmt = conn.prepareStatement(query);
		
		stmt.setInt(1, std.getId());
		stmt.setInt(2, 1);
		stmt.setInt(3, 0);
		
		stmt.execute();
	}
	
	public void insertProfessor(Professor professor) throws Exception
	{
		Connection conn = DBConnection.getConnection();
		
		String query = "INSERT INTO professors (p_id, Department, specialization) VALUES (?, ?, ?)";
		
		PreparedStatement stmt = conn.prepareStatement(query);
		
		stmt.setInt(1,professor.getId());
		stmt.setString(3,professor.getSpecialization());
		stmt.setString(2,professor.getDepartment());
		
		stmt.execute();
	}
	
}
