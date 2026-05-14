package com.University.DTO;

public class StudentProfDTO 
{
    private int id;
    private String name;
    private String email;
    private int semester;
    private int credits;
    private String courseCode;
    private String courseTitle;
    
    
	@Override
	public String toString() 
	{
		return "StudentProfDTO [id=" + id + ", name=" + name + ", email=" + email + ", semester=" + semester
				+ ", credits=" + credits + ", courseCode=" + courseCode + ", courseTitle=" + courseTitle + "]";
	}
	public int getId() 
	{
		return id;
	}
	public void setId(int id) 
	{
		this.id = id;
	}
	public String getName() 
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	public String getEmail() 
	{
		return email;
	}
	public void setEmail(String email) 
	{
		this.email = email;
	}
	public int getSemester() 
	{
		return semester;
	}
	public void setSemester(int semester) 
	{
		this.semester = semester;
	}
	public int getCredits() 
	{
		return credits;
	}
	public void setCredits(int credits) 
	{
		this.credits = credits;
	}
	public String getCourseCode() 
	{
		return courseCode;
	}
	public void setCourseCode(String courseCode) 
	{
		this.courseCode = courseCode;
	}
	public String getCourseTitle() 
	{
		return courseTitle;
	}
	public void setCourseTitle(String courseTitle) 
	{
		this.courseTitle = courseTitle;
	}
    
    
}
