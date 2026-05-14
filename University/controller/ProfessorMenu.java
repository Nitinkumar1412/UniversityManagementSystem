package com.University.controller;

import java.util.List;
import java.util.Scanner;

import com.University.DTO.StudentProfDTO;
import com.University.model.Professor;
import com.University.model.course;
import com.University.service.CourseService;
import com.University.service.EnrollmentService;
import com.University.service.ProfessorService;
import com.University.util.sessionManager;

public class ProfessorMenu 
{
	public void showprofessorMenu()
	{
		System.out.println();
		System.out.println("1.View Your Courses");
		System.out.println("2.Mangage courses");
		System.out.println("3.View Enrolled Students");
		System.out.println("4.Logout");
		System.out.println();
		System.out.print("Enter your choice: ");
	}
	
	public void ProfessorChoice(int choice, Professor professor)
	{
		EnrollmentService enrollmentService = new EnrollmentService();
		CourseService courseService = new CourseService();
		ProfessorService professorService = new ProfessorService();
		Scanner input = new Scanner(System.in);
		
		switch(choice)
		{
			case 1:
				professorService.viewCourseAsProfId(professor);
				break;
			case 2://manage courses:
				int mChoice = -1;
				while(mChoice != 4) {
					System.out.println("\n========== Manage Course Menu ===========\n");
					System.out.println("1. View Courses");
					System.out.println("2. View/Update Credits of a course");
					System.out.println("3. View/Update Syllabus");
					System.out.println("4. Back");
					mChoice = Integer.parseInt(input.nextLine());
					switch (mChoice) {
						case 1:
							professorService.viewCourseAsProfId(professor);
							break;
						case 2:
							System.out.println("====== View / Update Credits ======");
							System.out.println("1. View Credits of a course");
							System.out.println("2. Change Credits of a course");
							System.out.println("3. Back to Manage Course menu");
							System.out.println("\nEnter your choice: ");
							int iChoice = Integer.parseInt(input.nextLine());
							switch(iChoice){
								case 1:
									System.out.println("Enter the course code: ");
									String course_code = input.nextLine();
									professorService.viewCourseCredits(course_code.toUpperCase());
									break;
								case 2:
									System.out.println("Enter the course code: ");
									course_code = input.nextLine();
									professorService.updateCourseCredits(course_code.toUpperCase(), professor.getId());
									break;
								case 3:
									break;
								default:
									System.out.println("Invalid choice!");
							}
							break;
						case 3:
							System.out.println("====== View / Update Syllabus ======");
							System.out.println("1. View Syllabus");
							System.out.println("2. Change Syllabus");
							System.out.println("3. Back to Manage Course menu");
							System.out.println("\nEnter your choice: ");
							iChoice = Integer.parseInt(input.nextLine());
							switch(iChoice){
								case 1:
									System.out.println("Enter the course code: ");
									String course_code = input.nextLine();
									professorService.viewCourseSyllabus(course_code.toUpperCase());
									break;
								case 2:
									System.out.println("Enter the course code: ");
									course_code = input.nextLine();
									professorService.updateCourseSylabus(course_code.toUpperCase(), professor.getId());
									break;
								case 3:
									break;
								default:
									System.out.println("Invalid choice!");
							}
							break;
						case 4:
							break;
						default:
							System.out.println("Invalid Choice! try again...");
					}
				}
				break;				
			case 3:
				List<StudentProfDTO> studentprofDTO = enrollmentService.viewEnrolledStudent(professor.getId());
				if (studentprofDTO.isEmpty()) {
				    System.out.println("No students enrolled\n");
				} else {
				    // Print table header
				    System.out.printf("%-10s %-20s %-10s %-10s %-30s%n", 
				                      "s_id", "Name", "Semester", "CourseCode", "CourseTitle");
				    System.out.println("-----------------------------------------------------------------------------------");

				    // Print each student in tabular format
				    for (StudentProfDTO s : studentprofDTO) {
				        System.out.printf("%-10d %-20s %-10d %-10s %-30s%n", 
				                          s.getId(), 
				                          s.getName(), 
				                          s.getSemester(), 
				                          s.getCourseCode(), 
				                          s.getCourseTitle());
				    }
				}

			case 4:
				sessionManager.logout();
				break;
			default:
				System.out.println("INVALID input!! TRY AGAIN!!");
				break;
		}
	}
}
