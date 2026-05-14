package com.University.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.University.DTO.Schedule;
import com.University.databaseAccess.CourseDAO;
import com.University.databaseAccess.ScheduleDAO;
import com.University.databaseAccess.UserDAO;
import com.University.enums.CourseStatus;
import com.University.model.course;
import com.University.model.student;

public class StudentService 
{
	private Scanner input = new Scanner(System.in);
	private UserDAO userDAO = new UserDAO();
	private CourseDAO courseDAO = new CourseDAO();
	private ScheduleDAO scheduleDAO = new ScheduleDAO();
	private EnrollmentService enrollmentService = new EnrollmentService();
	
	public void viewAvailableCourses(int sem) 
	{		
	    List<course> courses = courseDAO.getCoursesBySemester(sem);

	 // 1. Print the Table Header
	    System.out.println();
	    System.out.println("+----------------------------------------------------------------------------------------------------------------------------------------+");
	    System.out.printf("| %-15s | %-50s | %-7s | %-30s | %-20s | %-10s |%n", "COURSE CODE", "TITLE", "CREDITS","Professor","Prerequisites");
	    System.out.println("+----------------------------------------------------------------------------------------------------------------------------------------+");

	    // 2. Loop through the list 
	    for (course course : courses) {
	    	ArrayList<String> prereqs = courseDAO.fetchPrerequisites(course.getId());
	    	String prereqsString = prereqs.isEmpty()?"None":String.join(",",prereqs);
	        System.out.printf("| %-15s | %-50s | %-7d | %-30s | %-20s | %-10s |%n", 
	            course.getId(),
	            course.getCourseName(), 
	            course.getCredits(),
	            course.getProfessor(),
	            prereqsString);
	    }

	    // 3. Print the Table Footer
	    System.out.println("+----------------------------------------------------------------------------------------------------------------------------------------+");
	    System.out.println();
	}
	
	public void viewAvailableCourses(int studentId, int sem) 
	{
		List<course> courses = courseDAO.getCoursesBySemester(sem);

		 // 1. Print the Table Header
			
		    System.out.println("\n+-------------------------------------------------------------------------------------------------------------------------------------------------------+");
		    System.out.printf("| %-15s | %-50s | %-7s | %-30s | %-20s | %-12s |%n", "COURSE CODE", "TITLE", "CREDITS","Professor","Prerequisites","Status");
		    System.out.println("+-------------------------------------------------------------------------------------------------------------------------------------------------------+");

		    // 2. Loop through the list 
		    for (course course : courses) 
		    {
		    	ArrayList<String> prereqs = courseDAO.fetchPrerequisites(course.getId());
		    	String prereqsString = prereqs.isEmpty()?"None":String.join(",",prereqs);
		        System.out.printf("| %-15s | %-50s | %-7d | %-30s | %-20s | %-12s |%n", 
		            course.getId(),
		            course.getCourseName(), 
		            course.getCredits(),
		            course.getProfessor(),
		            prereqsString,String.valueOf(courseDAO.fetchCourseStatus(studentId, course.getId())));
		    }
		    
		    // 3. Print the Table Footer
		    System.out.println("+-------------------------------------------------------------------------------------------------------------------------------------------------------+\n");
		    
	}
	
	
	public void registerCourses(student s) {
		System.out.println("\n==================Register Course Menu==================\n");
		viewAvailableCourses(s.getId(),s.getSemester());
		System.out.println("\nEnter the course code for which you want to register: ");
		String courseCode = input.nextLine();
		enrollmentService.enrolledservice(s.getId(),courseCode,s.getSemester());
	}
	
	public void dropCourse(int studentId, String course_code) {
		CourseStatus status = courseDAO.fetchCourseStatus(studentId, course_code);
		switch(status) {
		case CourseStatus.COMPLETED :
			System.out.println("CourseAlreadyCompleted : cannot drop a COMPLETED course!");
			break;
		case CourseStatus.ONGOING:
			enrollmentService.dropCourse(studentId,course_code);
			break;
		default:
			System.out.println("CourseUnregistered : cannot drop an UNREGISTERED course!");
		}
	}
	
	public void viewSchedule(int sem) {
		System.out.println("\n+-----+------------+------------+-------------+----------+-------------------+");
		System.out.println("| day | start_time | end_time   | course_code | location | professor         |");
		System.out.println("+-----+------------+------------+-------------+----------+-------------------+");
		List<Schedule> complaintList = scheduleDAO.viewSchedule(sem);
		for(Schedule s : complaintList) {
			System.out.printf("| %-3s | %-10s | %-10s | %-11s | %-8s | %-18s|\n",s.getDay(), s.getStart_time(),s.getEnd_time(),s.getCourse_code(),s.getLocation(),s.getProfessor());
		}
		System.out.println("+-----+------------+------------+-------------+----------+-------------------+\n");
	}
}