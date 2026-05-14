package com.University.model;

public class Erollment 
{
	private String studentId;
	private String courseId;
	private String semester;
	private String status;
	private String enrollmentId;
	
	
	public Erollment(String studentId, String courseId, String semester, String status, String enrollmentId) 
	{
		
		super();
		this.studentId = studentId;
		this.courseId = courseId;
		this.semester = semester;
		this.status = status;
		this.enrollmentId = enrollmentId;
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

	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getEnrollmentId() {
		return enrollmentId;
	}


	
}
