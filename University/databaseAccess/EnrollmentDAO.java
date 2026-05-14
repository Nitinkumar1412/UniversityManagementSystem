package com.University.databaseAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.University.DTO.StudentProfDTO;
import com.University.enums.CourseStatus;
import com.University.util.DBConnection;

public class EnrollmentDAO 
{
	public void insertEnrollment(String courseCode, int studentId, int semester )
	{
		Connection conn = DBConnection.getConnection();
		
		String query = "INSERT INTO Enrollments (student_id, course_code, semester, status) VALUES (?, ?, ?, ?)";
		
		try 
		{
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, studentId);
			stmt.setString(2, courseCode.toUpperCase());
			stmt.setInt(3, semester);
			stmt.setString(4,(CourseStatus.ONGOING).toString());
			
			stmt.executeUpdate();
			
			PreparedStatement ps = conn.prepareStatement("select *from courses where course_code = ?");
			ps.setString(1, courseCode);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) 
			{
				int credits = rs.getInt("credits");
				PreparedStatement ps2 = conn.prepareStatement("UPDATE students set credits = credits + ? where s_id = ?");
				ps2.setInt(1, credits);
				ps2.setInt(2, studentId);			
				ps2.execute();
			}

			PreparedStatement ps2 = conn.prepareStatement("UPDATE courses set enrolled_count = enrolled_count+1 where course_code = ?");
			ps2.setString(1,courseCode);
			ps2.executeUpdate();
			
			System.out.println("Course Enrolled Successfully!\n\n");
		} 
		catch (SQLException ex) 
		{
			// TODO Auto-generated catch block
			System.out.println("Invalid course code");
		}
	
	}
	
	public boolean isAlreadyEnrolled(int studentId, String courseCode)
	{
		
		try 
		{
			Connection conn = DBConnection.getConnection();
			
			String query = 	"SELECT * FROM enrollments where student_id = ? and course_code = ?";
			
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, studentId);
			stmt.setString(2, courseCode);
			
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
			{
				return true; 
			}
		} 
		catch (SQLException ex) 
		{
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		
		return false;
	}
	
	public void updateCourseStatus(int studentId, String courseCode, String status )
	{
		try 
		{
			Connection conn = DBConnection.getConnection();
			
			String query = "UPDATE status SET status = ? WHERE student_id = ? AND course_code = ?";
			
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, status);
			stmt.setInt(2, studentId);
			stmt.setString(3, courseCode);

			stmt.execute();
			
		} 
		catch (SQLException ex) 
		{
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}
	
	public List<StudentProfDTO> getStudentsByProfessor(int professorId) 
	{
	    List<StudentProfDTO> list = new ArrayList<>();

	    String query = "SELECT s.s_id, u.name, u.email, s.semester, s.credits, c.course_code, c.title " +
	                   "FROM enrollments e " +
	                   "JOIN students s ON e.student_id = s.s_id " +
	                   "JOIN courses c ON e.course_code = c.course_code " +
	                   "JOIN users u ON u.user_id = s.s_id " +
	                   "WHERE c.professor_id = ? "
	                   +"ORDER BY c.course_code";

	    try (Connection con = DBConnection.getConnection();PreparedStatement ps = con.prepareStatement(query)) 
	    {

	        ps.setInt(1, professorId);
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) 
	        {
	            StudentProfDTO dto = new StudentProfDTO();

	            dto.setId(rs.getInt("s_id"));
	            dto.setName(rs.getString("name"));
	            dto.setEmail(rs.getString("email"));
	            dto.setSemester(rs.getInt("semester"));
	            dto.setCredits(rs.getInt("credits"));
	            dto.setCourseCode(rs.getString("course_code"));
	            dto.setCourseTitle(rs.getString("title"));

	            list.add(dto);
	        }

	    } 
	    catch (Exception e) 
	    {
	        e.printStackTrace();
	    }

	    return list;
	}

	//for dropping courses:
	public void dropCourse(int studentId, String course_code){
		String query = "DELETE FROM Enrollments where student_id = ? AND course_code = ?";
		try(Connection con = DBConnection.getConnection()){
				PreparedStatement stmt = con.prepareStatement(query);
				stmt.setInt(1, studentId);
				stmt.setString(2, course_code);
				
				stmt.execute();
				
				PreparedStatement ps = con.prepareStatement("select * from courses where course_code = ?");
				ps.setString(1, course_code);
				ResultSet rs = ps.executeQuery();
				if(rs.next()) 
				{
					int credits = rs.getInt("credits");
					PreparedStatement ps2 = con.prepareStatement("UPDATE students set credits = credits - ? where s_id = ?");
					ps2.setInt(1, credits);
					ps2.setInt(2, studentId);			
					ps2.execute();
				}
				
				System.out.println("Course Dropped Successfully!\n");
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void updateSemester(int StudentId)
	{
		try(Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement("UPDATE students set semester = semester + 1 where s_id = ?")){
			ps.setInt(1, StudentId);
			ps.executeUpdate();
		}
		catch(SQLException e) {
			System.out.println(e);
		}
	}
	
	
	
}
