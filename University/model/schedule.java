package com.University.model;

public class schedule 
{
	private String scheduleId;
	private String CourseId;
	private String day;
	private String time;
	private String room;
	
//    constructor...
	
	public schedule(String scheduleId, String courseId, String day, String time, String room) 
	{
		super();
		this.scheduleId = scheduleId;
		CourseId = courseId;
		this.day = day;
		this.time = time;
		this.room = room;
	}

	public String getScheduleId() 
	{
		return scheduleId;
	}

	public String getCourseId() {
		return CourseId;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}
	
	
}
