package com.University.controller;

import java.util.List;
import java.util.Scanner;

import com.University.databaseAccess.CourseDAO;
import com.University.databaseAccess.EnrollmentDAO;
import com.University.model.course;
import com.University.model.schedule;
import com.University.model.user;
import com.University.service.ComplaintService;
import com.University.service.CourseService;
import com.University.service.GradeService;
import com.University.util.sessionManager;
import static com.University.util.TextColour.*;
public class AdminMenu 
{
	Scanner input = new Scanner(System.in);
	public void showAdminMenu()
	{
		
		System.out.println("1.Add Courses");
		System.out.println("2.Delete Courses");
		System.out.println("3.Assign Professor");
		System.out.println("4.Assign Grades");
		System.out.println("5.Handle Complaints");
		System.out.println("6.View All courses");
		System.out.println("7.Logout");
	}
	
	public void adminChoice(int choice , user usr)
	{
		CourseDAO coursedao = new CourseDAO();
		EnrollmentDAO enrollmentdao = new EnrollmentDAO();
		CourseService courseService = new CourseService();
		GradeService gradeservice = new GradeService();
		ComplaintService complaintservice = new ComplaintService();
		switch(choice)
		{
			case 1:
				System.out.println("\nAdd the new Course or type back to return to previous menu : ");
				System.out.print("Enter course Code: ");
				String course_code = input.nextLine();
				if(course_code.equalsIgnoreCase("back")) break;
				System.out.print("Enter course Title: ");
				String course_Title = input.nextLine();
				System.out.print("Enter course Credits(2 or 4): ");
				int credits = Integer.parseInt(input.nextLine());
				
				System.out.print("Enter course Semester: ");
				int sem = Integer.parseInt(input.nextLine());
				
				if(coursedao.addCourse(course_code.toUpperCase(), course_Title, credits, sem))
				{
					System.out.println("course ADDED successfully\n");
				}
				else
				{
					System.out.println("invalid credentials--\n");
				}
				break;
			
			case 2:
				System.out.println("\nDelete the course--");
				System.out.println("Enter the course code which you want to delete: ");
				String courseCode = input.nextLine();
				
				if(coursedao.deleteCourse(courseCode.toUpperCase()))
				{
					System.out.println("Course Deleted successfully--\n");
				}
				else
				{
					System.out.println("Invalid course code try again with valid code--\n");
				}
				break;
			case 3: 
				System.out.println("Assign the professor--");
				System.out.println("Enter the professor Id: ");
				int professorId = input.nextInt();
				input.nextLine();
				System.out.println("Enter the Course code: ");
				String coursecode = input.nextLine();
				
				if(coursedao.assignProfessor(coursecode.toUpperCase(), professorId))
				{
					System.out.println("Professor is assigned---\n");
				}
				else
				{
					System.out.println("Invalid professorId/courseId try Again--\n");
				}
				break;
				
			case 4:
				/*
				System.out.println("Assign the grades: ");
				System.out.println("Enter the Student Id: ");
				int studentId = input.nextInt();
				input.nextLine();
				System.out.println("Enter the Course Id: ");
				int courseId = input.nextInt();
				input.nextLine();
				System.out.println("Enter the Semester: ");
				int semester = input.nextInt();
				input.nextLine();
				System.out.println("Enter the grades: ");
				String grade = input.nextLine();
				gradeservice.assignGrade(studentId, courseId, semester, grade);
				break;
				*/
				
				 System.out.println("Assign the grades: ");
				System.out.println("Enter the Student Id: ");
				int studentId = input.nextInt();
				input.nextLine();
				
				List<course> enrolledCourses = coursedao.getEnrolledCoursesForStudent(studentId);
				
				if (enrolledCourses.isEmpty()) {
					System.out.println("\n[!] This student is not registered for any courses.");
					break;
				}
				
				courseService.displayEnrolledCoursesForStudent(studentId);
				
				System.out.println("\n--- Assigning Grades for Student ID: " + studentId + " ---");
				
				for (course c : enrolledCourses) {
					
					// We automatically know the ID and Semester from the database
					int currentCourseId = Integer.parseInt(c.getId()); 
					int currentSemester = c.getSemester();
					
					System.out.println("\nCourse: " + c.getCourseName() + " (ID: " + currentCourseId + ")");
					System.out.print("Enter grade (A, B, C, D, E, F) or type 'SKIP' to skip: ");
					
					String grade = input.nextLine().toUpperCase();
					
					// Give the admin a way to skip a subject if they don't have the grade yet
					if (grade.equals("SKIP")) {
						System.out.println("Skipped " + c.getCourseName() + ".");
						continue;
					}
					
					// Automatically assign it using the fetched data
					gradeservice.assignGrade(studentId, currentCourseId, currentSemester, grade);
					System.out.println("--> Grade '" + grade + "' assigned successfully!");
				}
				
				System.out.println("\nFinished assigning grades for Student ID: " + studentId + "!");
				
				double cgpa = gradeservice.CalculateCGPA(studentId);
				if(cgpa>5)
				{
					enrollmentdao.updateSemester(studentId);
					System.out.println("CONGRATULATIONS YOU ARE IN NEXT SEMESTER");
				}
				break;
				 
			case 5:
				boolean running = true;
				while (running) {
					System.out.println("\n-----Complaints------");
					System.out.println("1.view All complaints");
					System.out.println("2.Update the Complaint status");
					System.out.println("3." + color("Exit", RED, HIGH_INTENSITY));
					int cChoice = input.nextInt();
					switch(cChoice)
					{
						case 1:
							complaintservice.viewAllComplaint();
							 break;
						case 2:
							System.out.print("Enter the Complaint Id for whom you want to resolve comlpaint: ");
							studentId = input.nextInt();
							input.nextLine();
							System.out.println("enter resolved text: ");
							String text = input.nextLine();
							complaintservice.resolveComplaint(studentId, text);
							break;
						case 3: 
							running = false;
							break;
					}
				}
				break;
			case 6: 
				System.out.println("---All courses---");
				List<course> courses = courseService.getAllCourses();
				
				if (courses.isEmpty()) {
					System.out.println("[!] No courses available in the system.");
				} else {
					
					System.out.println("+-----------------+------------------------------------------+------------+------------+----------------------+");
					System.out.printf("| %-15s | %-40s | %-10s | %-10s | %-20s |%n", 
							"COURSE CODE", "TITLE", "CREDITS", "SEMESTER", "PROFESSOR");
					System.out.println("+-----------------+------------------------------------------+------------+------------+----------------------+");
					
					
					for (course c : courses) {
						System.out.printf("| %-15s | %-40s | %-10d | %-10d | %-20s |%n", 
							c.getId(), c.getCourseName(), c.getCredits(), c.getSemester(), c.getProfessor());
					}
					
					System.out.println("+-----------------+------------------------------------------+------------+------------+----------------------+\n");
				}
				break;
			case 7:
				sessionManager.logout();
				System.out.println(color("LOGOUT SUCCESSFULLY", GREEN));
				break;
		}
	}
}
