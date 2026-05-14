package com.University.DTO;

public class Schedule 
{
	private String day;
	private String start_time;
	private String end_time;
	private String course_code;
	private String location;
	private String professor;
	
	//setters and getters:
	public String getDay() 
	{
		return day;
	}
	public Schedule(String day, String start_time, String end_time, String course_code, String location,
			String professor) 
	{
		super();
		this.day = day;
		this.start_time = start_time;
		this.end_time = end_time;
		this.course_code = course_code;
		this.location = location;
		this.professor = professor;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getCourse_code() {
		return course_code;
	}
	public void setCourse_code(String course_code) {
		this.course_code = course_code;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getProfessor() {
		return professor;
	}
	public void setProfessor(String professor) {
		this.professor = professor;
	}
	
	
}