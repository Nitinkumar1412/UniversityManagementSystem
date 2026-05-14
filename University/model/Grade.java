package com.University.model;

import com.University.enums.GradeType;

public class Grade 
{
	private String studentId;
	private String courseId;
	private String semester;
	private GradeType grade;
	
	
	public Grade(String studentId, String courseId, String semester, GradeType grade) 
	{
		super();
		this.studentId = studentId;
		this.courseId = courseId;
		this.semester = semester;
		this.grade = grade;
	}


	public String getStudentId() {
		return studentId;
	}


	public String getCourseId() {
		return courseId;
	}


	public String getSemester() {
		return semester;
	}


	public GradeType getGrade() {
		return grade;
	}


	public void setGrade(GradeType grade) {
		this.grade = grade;
	}
	
	
	
}
