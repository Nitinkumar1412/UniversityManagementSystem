package com.University.service;

import com.University.databaseAccess.CourseDAO;
import com.University.databaseAccess.GradeDAO;
import com.University.exception.GradeAlreadyExistException;

public class AdministrationService 
{
	GradeDAO gradedao = new GradeDAO();
	
	public void assignGrades(int studentId, int CourseId,int semester, String grade)
	{
		if(gradedao.gradeExists(studentId, CourseId))
		{
			throw new GradeAlreadyExistException("student grade already exists");
		}
		else
		{
			gradedao.insertGrade(studentId, CourseId, semester, grade);
		}
	}
	
	CourseDAO coursedao = new CourseDAO();
	
}
