package com.University.databaseAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.University.DTO.GradeDTO;
import com.University.util.DBConnection;

public class GradeDAO 
{
	public boolean gradeExists(int studentId, int courseId)
	{
		try
		{
			Connection conn = DBConnection.getConnection();
		
			String query = "SELECT * FROM grade WHERE student_id = ? AND course_id = ?";
		
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, studentId);
			stmt.setInt(2, courseId);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next())
			{
				return true;
			}
		} 
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	public void insertGrade(int studentId,int courseId, int semester, String grade)
	{
		try
		{
			String course_code = null;
			Connection conn = DBConnection.getConnection();
			
			String query = "INSERT INTO grade (student_id, Course_id, semester, grade) VALUES (?, ?, ?, ?)";
			
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1,studentId);
			stmt.setInt(2,courseId);
			stmt.setInt(3,semester);
			stmt.setString(4,grade);
			
			stmt.execute();
			
			PreparedStatement ps1 = conn.prepareStatement("Select course_code from courses where course_id = ?");
			ps1.setInt(1, courseId);
			
			ResultSet rs = ps1.executeQuery();
			if(rs.next()) {
				course_code = rs.getString("course_code");
			}
			
			
			String query2 = "UPDATE enrollments set status='COMPLETED' where student_id=? and course_Code = ?";
			
			PreparedStatement ps2 = conn.prepareStatement(query2);
			ps2.setInt(1, studentId);
			ps2.setString(2,course_code);
			
			ps2.executeUpdate();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public void updateGrade(int studentId, int courseId, String grade) 
	{

        try 
        {
        	String course_code = null;
            Connection conn = DBConnection.getConnection();

            String query = "UPDATE grade SET grade = ? WHERE student_id = ? AND course_id = ?";
            
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, grade);
            stmt.setInt(2, studentId);
            stmt.setInt(3, courseId);

            stmt.executeUpdate();
            
            PreparedStatement ps1 = conn.prepareStatement("Select course_code from courses where course_id = ?");
			ps1.setInt(1, courseId);
			
			ResultSet rs = ps1.executeQuery();
			if(rs.next()) {
				course_code = rs.getString("course_code");
			}
			
			
			String query2 = "UPDATE enrollments set status='COMPLETED' where student_id=? and course_Code = ?";
			
			PreparedStatement ps2 = conn.prepareStatement(query2);
			ps2.setInt(1, studentId);
			ps2.setString(2,course_code);
			
			ps2.executeUpdate();

        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
	}
	
	public List<GradeDTO> fetchGrade(int studentId)
	{
		try(Connection conn = DBConnection.getConnection();)
		{
			String query = "SELECT *FROM grade WHERE student_id = ?";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, studentId);
			
			ResultSet rs = stmt.executeQuery();
			
			List<GradeDTO> gradedto = new ArrayList<GradeDTO>();
			while(rs.next())
			{
				int CourseId = rs.getInt("course_id");
				String grade= rs.getString("grade");
				int semester = rs.getInt("semester");
				
				gradedto.add(new GradeDTO(CourseId,grade,semester));
			}
			return gradedto;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		return null;
	}
	
	public List<GradeDTO> fetchGrade(int studentId,int semester)
	{
		try(Connection conn = DBConnection.getConnection();)
		{
			String query = "SELECT *FROM grade WHERE student_id = ? AND semester = ? ";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, studentId);
			stmt.setInt(2, semester);
			
			ResultSet rs = stmt.executeQuery();
			
			List<GradeDTO> gradedto = new ArrayList<GradeDTO>();
			while(rs.next())
			{
				int CourseId = rs.getInt("course_id");
				String grade= rs.getString("grade");
				
				gradedto.add(new GradeDTO(CourseId,grade,semester));
			}
			
			return gradedto;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		return null;
	}
}
