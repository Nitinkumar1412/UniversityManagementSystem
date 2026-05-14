package com.University.DTO;

public class GradeDTO 
{
	private int CourseId;
	String grade;
	int semester;
	
	
	
	
	@Override
	public String toString() 
	{
		return "GradeDTO [CourseId=" + CourseId + ", grade=" + grade + ", semester=" + semester + "]";
	}
	
	public GradeDTO() {
		
	}
	public GradeDTO(int courseId, String grade, int semester) 
	{
		super();
		CourseId = courseId;
		this.grade = grade;
		this.semester = semester;
	}


	public int getCourseId() {
		return CourseId;
	}
	public void setCourseId(int courseId) {
		CourseId = courseId;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public int getSemester() {
		return semester;
	}
	public void setSemester(int semester) {
		this.semester = semester;
	}
	
	
}
