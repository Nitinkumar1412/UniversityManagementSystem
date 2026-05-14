package com.University.service;

import java.sql.SQLException;
import java.util.List;

import com.University.DTO.StudentProfDTO;
import com.University.databaseAccess.EnrollmentDAO;
import com.University.exception.CourseAlreadyEnrolled;

public class EnrollmentService 
{
	private EnrollmentDAO enrollmentdao = new EnrollmentDAO();
	
	public void enrolledservice(int studentId, String courseCode, int semester)
	{
		try
		{
			if(enrollmentdao.isAlreadyEnrolled(studentId, courseCode))
			{
				throw new CourseAlreadyEnrolled("The Course "+courseCode+" is already registered!");
			}
			enrollmentdao.insertEnrollment( courseCode, studentId, semester);
		}
		catch(CourseAlreadyEnrolled ex)
		{
			System.out.println(ex);
		}		
		
		
	}
	
	public List<StudentProfDTO> viewEnrolledStudent(int professorId)
	{
		try
		{
			
			return enrollmentdao.getStudentsByProfessor(professorId);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		return null;
	}
	public void insertEnrollment(String courseId, int studentId, int semester) 
	{
		enrollmentdao.insertEnrollment(courseId, studentId, semester);
		
	}

	public void dropCourse(int studentId, String course_code) {
		enrollmentdao.dropCourse(studentId, course_code);
		
	}
	
}
