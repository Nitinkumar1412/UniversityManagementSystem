# University Management System

A comprehensive Java-based console application for managing university operations including student enrollment, course management, grading, and administrative functions.

## 📋 Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Architecture](#architecture)
- [Database Schema](#database-schema)
- [Prerequisites](#prerequisites)
- [Installation & Setup](#installation--setup)
- [Usage](#usage)
- [Project Structure](#project-structure)
- [API Documentation](#api-documentation)

## 🎯 Overview

The University Management System is a robust Java application that provides a complete solution for managing university operations. It supports multiple user roles (Students, Professors, and Administrators) with role-based access control and comprehensive functionality for academic management.

## ✨ Features

### 👨‍🎓 Student Features
- Course enrollment and viewing enrolled courses
- Grade viewing and academic record management
- Complaint submission system
- Schedule viewing
- Profile management

### 👨‍🏫 Professor Features
- Course assignment and management
- Student grade assignment
- Schedule management
- Profile management

### 👨‍💼 Administrator Features
- User management (add/remove students, professors)
- Course creation and management
- System-wide reporting
- Complaint resolution
- Schedule management

### 🔐 Authentication & Security
- Secure user registration and login
- Role-based access control
- Password encryption utilities
- Session management

## 🏗️ Architecture

The application follows a layered architecture pattern:

### MVC Pattern
- **Model**: Data entities (User, Student, Professor, Course, etc.)
- **View**: Console-based user interface through menu systems
- **Controller**: Menu controllers handling user interactions

### Service Layer
- Business logic encapsulation
- Transaction management
- Data validation

### Data Access Layer
- DAO (Data Access Object) pattern
- Database connection management
- CRUD operations

## 🗄️ Database Schema

The system uses MySQL database with the following main tables:

### Core Tables
- `users` - Base user information (id, name, email, password, role)
- `students` - Student-specific data (semester, credits)
- `professors` - Professor-specific data (department, specialization)
- `courses` - Course information (code, title, credits, semester, professor_id)
- `enrollments` - Student-course relationships
- `grades` - Student grades for courses
- `complaints` - Student complaint system
- `schedules` - Class schedules

### Relationships
- Users → Students/Professors (1:1 inheritance)
- Professors → Courses (1:N)
- Students → Enrollments → Courses (N:M)
- Students → Grades → Courses (1:N)
- Students → Complaints (1:N)

## 📋 Prerequisites

- **Java**: JDK 11 or higher
- **MySQL**: Version 8.0 or higher
- **MySQL Connector/J**: JDBC driver (included in lib/)
- **Operating System**: Windows/Linux/MacOS

## 🚀 Installation & Setup

### 1. Clone/Download the Project
```bash
# Navigate to your desired directory
cd /path/to/your/workspace
# Copy the project files to UniversityManagement folder
```

### 2. Database Setup
```sql
-- Create database
CREATE DATABASE university_db;

-- Run the schema creation scripts
-- (Note: Full schema creation scripts should be added to database_schema.sql)
```

### 3. Configure Database Connection
Edit `src/com/University/config/db.properties`:
```properties
db.url=jdbc:mysql://localhost:3306/university_db
db.user=your_mysql_username
db.password=your_mysql_password
```

### 4. Build the Application
Run the build script:
```bash
# On Windows
build.bat

# Or using PowerShell
.\build.ps1
```

### 5. Run the Application
The build script automatically runs the application after successful compilation.

## 💻 Usage

### Starting the Application
```bash
java -cp "bin;lib/mysql-connector-java-x.x.xx.jar" com.University.MainApp
```

### Main Menu Options
1. **Register**: Create new user account (Student/Professor)
2. **Login**: Authenticate existing user
3. **Exit**: Close the application

### User Workflows

#### Student Registration
1. Select "Register" from main menu
2. Enter name, email, password
3. Specify role as "student"
4. Account created successfully

#### Professor Registration
1. Select "Register" from main menu
2. Enter name, email, password
3. Specify role as "professor"
4. Enter department and specialization
5. Account created successfully

#### Login Process
1. Select "Login" from main menu
2. Enter email and password
3. Redirected to role-specific menu based on user type

## 📁 Project Structure

```
UniversityManagement/
├── src/
│   ├── module-info.java
│   └── com/University/
│       ├── MainApp.java                 # Application entry point
│       ├── TestApp.java                 # Testing utilities
│       ├── TestJDBC.java               # Database connectivity tests
│       ├── config/
│       │   └── db.properties           # Database configuration
│       ├── controller/                 # Menu controllers
│       │   ├── AdminMenu.java
│       │   ├── ProfessorMenu.java
│       │   └── StudentMenu.java
│       ├── databaseAccess/             # Data Access Objects
│       │   ├── ComplaintDAO.java
│       │   ├── CourseDAO.java
│       │   ├── EnrollmentDAO.java
│       │   ├── GradeDAO.java
│       │   ├── ScheduleDAO.java
│       │   └── UserDAO.java
│       ├── DTO/                       # Data Transfer Objects
│       │   ├── Schedule.java
│       │   └── StudentProfDTO.java
│       ├── enums/                     # Enumeration classes
│       │   ├── complaintStatus.java
│       │   ├── CourseStatus.java
│       │   ├── GradeType.java
│       │   └── Role.java
│       ├── exception/                 # Custom exceptions
│       │   ├── CourseAlreadyEnrolled.java
│       │   ├── GradeAlreadyExistException.java
│       │   └── userAlreadyExistException.java
│       ├── model/                     # Domain models
│       │   ├── Admin.java
│       │   ├── Complaint.java
│       │   ├── Course.java
│       │   ├── Enrollment.java
│       │   ├── Grade.java
│       │   ├── Professor.java
│       │   ├── Schedule.java
│       │   ├── Student.java
│       │   └── User.java
│       ├── service/                   # Business logic services
│       │   ├── AdministrationService.java
│       │   ├── AuthenticationService.java
│       │   ├── ComplaintService.java
│       │   ├── CourseService.java
│       │   ├── EnrollmentService.java
│       │   ├── GradeService.java
│       │   ├── ProfessorService.java
│       │   └── StudentService.java
│       └── util/                      # Utility classes
│           ├── DBConnection.java
│           ├── passwordUtil.java
│           └── sessionManager.java
├── lib/                              # External libraries
├── bin/                              # Compiled classes (generated)
├── build.bat                         # Windows build script
├── build.ps1                         # PowerShell build script
├── database_schema_fix.sql          # Database schema updates
└── README.md                         # This documentation
```

## 📚 API Documentation

### Core Classes

#### User Model Hierarchy
```java
public abstract class User {
    protected int id;
    protected String name;
    protected String email;
    protected String password;
    protected Role role;
}

public class Student extends User {
    private int currentCredits;
    private int semester;
}

public class Professor extends User {
    private String department;
    private String specialization;
}

public class Admin extends User {
    // Admin-specific properties
}
```

#### Course Model
```java
public class Course {
    private String id;           // Course code
    private String courseName;   // Course title
    private int credits;         // Credit hours
    private int semester;        // Semester number
    private String professor;    // Professor name
    private int enrolledCount;   // Current enrollment
}
```

### Key Services

#### AuthenticationService
- `register(User user)` - Register new user
- `login(String email, String password)` - Authenticate user

#### CourseService
- `getAllCourses()` - Retrieve all courses
- `getCoursesBySemester(int semester)` - Get semester courses
- `addCourse(String code, String title, int credits, int semester)` - Add new course

#### EnrollmentService
- `enrollStudent(int studentId, String courseCode)` - Enroll student in course
- `getEnrolledCourses(int studentId)` - Get student's enrolled courses

### Database Access Objects (DAO)

Each DAO provides CRUD operations for its respective entity:
- `UserDAO` - User management
- `CourseDAO` - Course management
- `EnrollmentDAO` - Enrollment management
- `GradeDAO` - Grade management
- `ComplaintDAO` - Complaint management

## 🔧 Configuration

### Database Configuration
Located in `src/com/University/config/db.properties`:
```properties
db.url=jdbc:mysql://localhost:3306/university_db
db.user=root
db.password=nitin
```

### Build Configuration
- **Source Directory**: `src/`
- **Output Directory**: `bin/`
- **Libraries**: `lib/` (MySQL Connector/J required)

## 🐛 Troubleshooting

### Common Issues

1. **MySQL Driver Not Found**
   - Download MySQL Connector/J JAR
   - Place in `lib/` directory
   - Ensure correct version compatibility

2. **Database Connection Failed**
   - Verify MySQL service is running
   - Check database credentials in `db.properties`
   - Ensure database `university_db` exists

3. **Compilation Errors**
   - Ensure JDK 11+ is installed
   - Check classpath includes MySQL driver
   - Verify all source files are present

4. **Runtime Exceptions**
   - Check database schema matches code expectations
   - Verify user permissions in database
   - Review application logs for detailed error messages

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

### Development Guidelines
- Follow Java naming conventions
- Add documentation for new methods
- Include unit tests for new features
- Update README for significant changes

## 📄 License
MIT license
