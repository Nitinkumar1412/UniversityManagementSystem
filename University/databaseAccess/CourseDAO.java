package com.University.databaseAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.University.enums.CourseStatus;
import com.University.model.Professor;
import com.University.model.course;
import com.University.util.DBConnection;

public class CourseDAO {

    // 🔹 Get all courses
	public List<course> getAllCourses() 
	    {

	        List<course> courseList = new ArrayList<>();

	        try 
	        {
	            Connection conn = DBConnection.getConnection();

	            String query = "SELECT c.course_code, c.title, c.credits, c.semester, u.name as professor_name FROM courses c JOIN users u ON c.professor_id = u.user_id ";
	            PreparedStatement stmt = conn.prepareStatement(query);

	            ResultSet rs = stmt.executeQuery();

	            while (rs.next()) {
	                String id = rs.getString("course_code");
	                String name = rs.getString("title");
	                int credits = rs.getInt("credits");
	                int semester = rs.getInt("semester");
	                String professor = rs.getString("professor_name");

	                course course = new course(id, name, credits,semester,professor);
	                courseList.add(course);
	            }

	        } 
	        catch (Exception ex) 
	        {
	            ex.printStackTrace();
	        }

	        return courseList;
	    }

    // 🔹 Get courses by semester
	 public List<course> getCoursesBySemester(int semester) 
	    {

	        List<course> courseList = new ArrayList<>();

	        try ( Connection conn = DBConnection.getConnection();)
	        {
	            String query = "SELECT c.course_code, c.title, c.credits, u.name as professor_name FROM courses c JOIN users u ON c.professor_id = u.user_id WHERE c.semester = ?";
	            PreparedStatement stmt = conn.prepareStatement(query);

	            /*
	              SELECT c.course_code, c.title, c.credits, u.name AS professor_name
			FROM courses c
			JOIN users u ON c.professor_id = u.user_id
			WHERE c.semester = ?
	             */
	            stmt.setInt(1, semester);

	            ResultSet rs = stmt.executeQuery();

	            while (rs.next()) 
	            {
	                String id = rs.getString("course_code");
	                String name = rs.getString("title");
	                int credits = rs.getInt("credits");
	                String professor = rs.getString("professor_name");
	                

	                course course = new course(id, name, credits,semester,professor);
	                courseList.add(course);
	            }

	        } 
	        catch (Exception e) 
	        {
	            e.printStackTrace();
	        }

	        return courseList;
	    }
    
    public boolean addCourse(String Course_code, String title, int credits, int semester)
    {
    	try
    	{
    		Connection conn = DBConnection.getConnection();
    		
    		String query = "INSERT INTO courses (course_code, title, credits, semester) VALUES(?, ?, ?, ?)";
    		
    		PreparedStatement stmt = conn.prepareStatement(query);
    		stmt.setString(1,Course_code);
    		stmt.setString(2,title);
    		stmt.setInt(3,credits);
    		stmt.setInt(4,semester);
    		
    		int row = stmt.executeUpdate();
    		return row>0;
    		
    		
    	}
    	catch(Exception ex)
    	{
    		ex.printStackTrace();
    	}
    	
    	return false;
    }
    public boolean deleteCourse(String course_code) // main me implement krne pr if condition me krna hai
    {
    	try 
    	{
    		Connection conn = DBConnection.getConnection();
    		
    		String query = "DELETE FROM courses WHERE course_code = ?";
    		
    		PreparedStatement stmt = conn.prepareStatement(query);
    		
    		stmt.setString(1,course_code);
    		
    		
    		int row = stmt.executeUpdate();
    		
    		return row>0;
    		
    	}
    	catch(Exception ex)
    	{
    		ex.printStackTrace();
    	}
    	
    	return false;
    }
    
    public boolean assignProfessor(String course_code, int professor_id)
    {
    	try
    	{
    		
    		Connection conn = DBConnection.getConnection();
    		String query = "UPDATE courses SET professor_id = ? WHERE course_code = ?";
    		PreparedStatement stmt = conn.prepareStatement(query);
    		stmt.setInt(1, professor_id);
    		stmt.setString(2, course_code);
    		
    		int row = stmt.executeUpdate();
    		return row>0;
    
    	}
    	catch(Exception ex)
    	{
    		ex.printStackTrace();
    	}
    	return false;
    }
    
    //get prerequisites by course code:
    public ArrayList<String> fetchPrerequisites(String course_code) 
    {
    	ArrayList<String> list = new ArrayList<>();
    	
    	String query = "select * from prerequisites where course_code = ?";
    	try(Connection conn = DBConnection.getConnection())
    	{
    		  PreparedStatement stmt = conn.prepareStatement(query);
    		  stmt.setString(1,course_code);
    		  
    		  ResultSet rs = stmt.executeQuery();
    		  
    		  while(rs.next()) {
    			  String pr = rs.getString("prereq_course_code");
    			  list.add(pr);
    		  }
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	return list;
    }
    
    public ArrayList<course> viewCourseAsProfId(Professor prof)
    {
    	ArrayList<course> courseList = new ArrayList<>();
    	String query = "Select * from courses where professor_id = ?";
    	
    	try(Connection conn = DBConnection.getConnection())
    	{
    		PreparedStatement stmt = conn.prepareStatement(query);
  		  	stmt.setInt(1,prof.getId());
  		  	
  		  	ResultSet rs = stmt.executeQuery();
  		  	while(rs.next())
  		  	{
  		  		String id = rs.getString("course_code");
  		  		String name = rs.getString("title");
  		  		int credits = rs.getInt("credits");
  		  		int semester = rs.getInt("semester");
  		  		int enrollmentCount = rs.getInt("enrolled_count");
  		  		course course = new course(id, name, credits,semester,prof.getName(),enrollmentCount);
  		  		
  		  		courseList.add(course);
  		  	}
  		  	
    	}
    	catch(Exception ex)
    	{
    		ex.printStackTrace();
    	}
    	
    	return courseList;
    }
    
  //get status of a course:
    public CourseStatus fetchCourseStatus(int s_id, String course_code) 
    {
    	String query = "select status from enrollments where student_id = ? and course_code = ?";
    	
    	try(Connection con = DBConnection.getConnection())
    	{
    		PreparedStatement stmt = con.prepareStatement(query);
    		stmt.setInt(1, s_id);
    		stmt.setString(2, course_code);
    		ResultSet rs = stmt.executeQuery();
    		if(rs.next()) 
    		{
    			String status = rs.getString("status");
    			switch(status) 
    			{
    			case "PENDING":
    				return CourseStatus.PENDING;
    			case "ONGOING":
    				return CourseStatus.ONGOING;
    			case "COMPLETED":    			
    				return CourseStatus.COMPLETED;
    			case "DROPPED":
    				return CourseStatus.DROPPED;
    			}
    		}
    	}
    	catch(Exception e) 
    	{
    		e.printStackTrace();
    	}
    	return CourseStatus.UNREGISTERED;
    }

 // Fetch courses a specific student is currently enrolled in
    public List<course> getEnrolledCoursesForStudent(int studentId) {
        List<course> list = new ArrayList<>();
        
        // Joining 3 tables: Enrollments (to find the student's courses), Courses (to get the course name), and Users (to get the professor's name)
        String query = "SELECT c.course_id, c.title, c.credits, e.semester, u.name as professor_name " +
                       "FROM enrollments e " +
                       "JOIN courses c ON e.course_code = c.course_code " +
                       "JOIN users u ON c.professor_id = u.user_id " +
                       "WHERE e.student_id = ?";
                       
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
             
             ps.setInt(1, studentId);
             ResultSet rs = ps.executeQuery();
             
             while (rs.next()) {
                 int id = rs.getInt("course_id");
                 String iD = String.valueOf(id);
                 String name = rs.getString("title");
                 int credits = rs.getInt("credits");
                 int semester = rs.getInt("semester"); 
                 String professor = rs.getString("professor_name");
                 
                 // course object and add to the list
                 list.add(new course(iD, name, credits, semester, professor));
             }
        } catch (Exception e) {
             e.printStackTrace();
        }
        return list;
    }


	public int viewCourseCredits(String course_code){
		int credits = -1;
		String query = "Select credits from courses where course_code = ?";
		try(Connection con = DBConnection.getConnection()){
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1,course_code);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				credits = rs.getInt("credits");
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return credits;
	}

	public boolean updateCredits(int newCredits, String course_code, int professor_id){
		String query = "update courses set credits = ? where course_code = ? and professor_id = ?";
		try(Connection con  = DBConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(query)){
			ps.setInt(1,newCredits);
			ps.setString(2,course_code);
			ps.setInt(3,professor_id);
			int rows = ps.executeUpdate();
			return rows>0;
		}
		catch(SQLException e){
			System.out.println("invalid courseCode, please enter the correct course code!");

		}
		return false;
	}

	public String viewCourseSyllabus(String course_code) {
		String syllabus = null;
		String query = "SELECT syllabus FROM courses WHERE course_code = ?";

		try (Connection con = DBConnection.getConnection();
			 PreparedStatement ps = con.prepareStatement(query)) {

			ps.setString(1, course_code);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					syllabus = rs.getString("syllabus");
				}
			}
		} catch (SQLException e) {
			System.out.println("Something went wrong pls try again.");
		}
		return syllabus;
	}

	public boolean updateSyllabus(String course_code, String newSyllabus , int professor_id) {
		String query = "UPDATE courses SET syllabus = ? WHERE course_code = ? AND professor_id = ?";

		try (Connection con = DBConnection.getConnection();
			 PreparedStatement ps = con.prepareStatement(query)) {

			ps.setString(1, newSyllabus); // First '?' is the syllabus content
			ps.setString(2, course_code); // Second '?' is the filter
			ps.setInt(3,professor_id);
			int rowsAffected = ps.executeUpdate();
			return rowsAffected > 0;

		} catch (SQLException e) {
			System.err.println("Error updating syllabus for course: " + course_code);
		}
		return false;
	}
}