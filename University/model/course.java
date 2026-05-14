package com.University.model;

public class course 
{
	private String id;
	private String courseName;
	private int credits;
	private int semester;
	private String professor;
	private int enrolledCount = 0;


	public course(String id, String courseName, int credits, int semester, String professor) 
	{
		super();
		this.id = id;
		this.courseName = courseName;
		this.credits = credits;
		this.semester = semester;
		this.professor = professor;
	}
	public course(String id, String courseName, int credits, int semester, String professor,int enrolledCount) 
	{
		super();
		this.id = id;
		this.courseName = courseName;
		this.credits = credits;
		this.semester = semester;
		this.professor = professor;
		this.enrolledCount = enrolledCount;
	}
	
	
	public int getEnrolledCount() {
		return enrolledCount;
	}
	public void setEnrolledCount(int enrolledCount) {
		this.enrolledCount = enrolledCount;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public String getProfessor() {
		return professor;
	}

	public void setProfessor(String professor) {
		this.professor = professor;
	}

	public String getCourseName() 
	{
		return courseName;
	}

	@Override
	public String toString() {
		return "course [id=" + id + ", courseName=" + courseName + ", credits=" + credits + ", semester=" + semester
				+ ", professor=" + professor + "]";
	}

	public void setCourseName(String courseName) 
	{
		this.courseName = courseName;
	}

	public int getCredits() 
	{
		return credits;
	}

	public void setCredits(int credits) 
	{
		this.credits = credits;
	}
	
	
	
	
}
