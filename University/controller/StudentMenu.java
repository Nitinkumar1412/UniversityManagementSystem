package com.University.controller;

import java.util.List;
import java.util.Scanner;

import com.University.databaseAccess.CourseDAO;
import com.University.enums.CourseStatus;
import com.University.model.course;
import com.University.model.student;
import com.University.model.user;
import com.University.service.ComplaintService;
import com.University.service.CourseService;
import com.University.service.GradeService;
import com.University.service.StudentService;
import com.University.util.sessionManager;

public class StudentMenu 
{
	
	public void showStudentMenu()
	{
		System.out.println("\n1.View Course");
		System.out.println("2.Register course");
		System.out.println("3.Drop Courses");
		System.out.println("4.View Schedule");
		System.out.println("5.View Performance");
		System.out.println("6.Submit a Complaint");
		System.out.println("7.View all my complaints");
		System.out.println("8.Logout");
		System.out.println("\nEnter your choice: ");
	}
	
	private Scanner input = new Scanner(System.in);
	private CourseService csc = new CourseService();
	public void studentChoice(int choice,student std)
	{
		ComplaintService compService = new ComplaintService();
		StudentService studentService = new StudentService();
		GradeService gradeservice = new GradeService();
		switch(choice)
		{
			case 1: 
				System.out.println("SEMESTER: "+std.getSemester());
				studentService.viewAvailableCourses(std.getId(),std.getSemester());
				
				//System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
				break;
			case 2:
				studentService.registerCourses(std);
				//System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
				break;
			case 3:
				System.out.println("Enter the course_code of the code you want to drop: ");
				String course_code = input.nextLine();
				studentService.dropCourse(std.getId(),course_code.toUpperCase());
				//System.out.println("\n---------------------------------------------------------------------------------------------\n");
				break;
			case 4:
				studentService.viewSchedule(std.getSemester());
				//System.out.println("\n---------------------------------------------------------------------------------------------\n");
				break;
			case 5:
				int Gchoice=-1;
				while(Gchoice!=3) {
					System.out.println("1 -> View CGPA");
					System.out.println("2 -> View SGPA");
					System.out.println("3 -> Back");
					Gchoice = input.nextInt();
					input.nextLine(); // Consume the newline character left by nextInt()

					switch (Gchoice) {
						case 1: 
							double cgpa = gradeservice.CalculateCGPA(std.getId());
							// Using printf to format the double to 2 decimal places
							System.out.printf("\n>>> Your current CGPA is: %.2f <<<\n", cgpa);
							break;
							
						case 2: 
							System.out.print("Enter Semester: ");
							int sem = input.nextInt();
							input.nextLine(); // Consume the newline character
							
							double sgpa = gradeservice.CalculateSGPA(std.getId(), sem);
							System.out.printf("\n>>> Your SGPA for semester %d is: %.2f <<<\n", sem, sgpa);
							break;
							
						case 3:
							System.out.println("Returning to main menu...");
							break;
							
						default:
							System.out.println("Invalid choice! Try again.");
					}
				}
				
				
				
				
				break;
			case 6:
				System.out.println("\n----------Submit Complaint----------");
				System.out.println("\nEnter you comlpaint description: ");
				String text = input.nextLine();
				//user currentusr =sessionManager.getCurrentUser();
				compService.filecomplaint(std.getId(),text);
				//System.out.println("\n---------------------------------------------------------------------------------------------\n");
				break;
			case 7:
				System.out.println("\n----------Your All complaints----------\n");
				compService.viewMyComplaints(std.getId());
				//System.out.println("\n---------------------------------------------------------------------------------------------\n");
				break;
			case 8:
				break;
			default:
				System.out.println("Invalid input! try again");
		}
	}
	
}
