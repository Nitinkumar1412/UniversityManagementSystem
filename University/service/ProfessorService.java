package com.University.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.University.DTO.StudentProfDTO;
import com.University.databaseAccess.CourseDAO;
import com.University.databaseAccess.EnrollmentDAO;
import com.University.model.Professor;
import com.University.model.course;

public class ProfessorService 
{
	private Scanner input = new Scanner(System.in);
	private CourseDAO courseDAO = new CourseDAO();
	//	if you want to this in controller layer do this 
	public void printStudentsForProfessor(int professorId) {
		
		EnrollmentDAO enrollmentDAO = new EnrollmentDAO();
	    List<StudentProfDTO> list = enrollmentDAO.getStudentsByProfessor(professorId);

	    if (list.isEmpty()) {
	        System.out.println("No students found for this professor.");
	        return;
	    }
	    
	    System.out.println("---- Student List ----");

	    for (StudentProfDTO s : list) {
	        System.out.println(
	            "ID: " + s.getId() +
	            ", Name: " + s.getName() +
	            ", Email: " + s.getEmail() +
	            ", Semester: " + s.getSemester() +
	            ", Credits: " + s.getCredits() +
	            ", Course: " + s.getCourseCode() +
	            " (" + s.getCourseTitle() + ")"
	        );
	    }	    
	}
	
	public void viewCourseAsProfId(Professor p) {
		ArrayList<course> courseList = courseDAO.viewCourseAsProfId(p);
		if(courseList.isEmpty()) {
			System.out.println("--------No courses Assigned------------\n");
		}
		else {
			// Print header line
			System.out.println("-----------------------------------------------------------------------------------------------------------------");
			System.out.printf("| S_No | %-50s | %-11s | %-7s | %-8s | %-14s |%n","Title","course_code","Credits","Semester","Enrolled_count");
			System.out.println("-----------------------------------------------------------------------------------------------------------------");

			// Print course rows
			int s_no = 1;
			for(course c : courseList) {
			    System.out.printf("| %-4d | %-50s | %-11s | %-7s | %-8s | %-14s |%n",
			    	s_no++,
			        c.getCourseName(),
			        c.getId(),
			        c.getCredits(),
			        c.getSemester(),
			        c.getEnrolledCount()
			    );
			}

			// Print footer line
			System.out.println("-----------------------------------------------------------------------------------------------------------------");

		}
	}

	public void updateCourseCredits(String course_code , int professor_id){
		System.out.println("Enter the new credits(2/4): ");
		int newCredits = Integer.parseInt(input.nextLine());
		if(newCredits == 2 || newCredits==4) {
			if (courseDAO.updateCredits(newCredits, course_code, professor_id)) {
				System.out.println("Credits updated successfully");
			} else {
				System.out.println("InvalidCourseCodeException : Please check the course code you have entered.");
			}
		}
		else{
			System.out.println("Invalid Credits entered!");
		}
	}
	public void viewCourseCredits(String course_code){
		int credits = courseDAO.viewCourseCredits(course_code);
		if(credits!=-1){
			System.out.println("Credits for the course "+course_code+" : "+credits);
		}
		else{
			System.out.println("Invalid course code entered.");
		}
	}
	public void viewCourseSyllabus(String course_code){
		String syllabus = courseDAO.viewCourseSyllabus(course_code);
		if(syllabus == null){
			System.out.println("No syllabus currently.");
		}
		else{
			System.out.println(syllabus);
		}
	}

	public void updateCourseSylabus(String course_code , int professor_id){
		System.out.println("Enter the new syllabus: ");
		String newSyllabus = input.nextLine();
		if(courseDAO.updateSyllabus(course_code,newSyllabus,professor_id)){
			System.out.println("Syllabus updated successfully");
		}
		else{
			System.out.println("InvalidCourseCodeException : Please check the course code you have entered.");
		}
	}
}
