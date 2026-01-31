This is a Java-based Student Management System developed as part of an academic project.
The system allows management of students, courses, enrollments, and grades using a JavaFX GUI and an SQLite database.
The project focuses on applying Object-Oriented Programming, JavaFX, and basic software architecture principles.


     Project Objectives
 
 -Practice Java and JavaFX development

-Apply MVC architecture

- with a relational database (SQLite)

-Understand DAO and Service Layer patterns

-Build a complete desktop application

 
   
    Main Features

-User login and registration

-Role-based access (Admin, Instructor, Student)

-Student management (add, update, delete, view)

-Course management

-Student enrollment into courses

-Grade management and GPA calculation

-Export data to CSV

-JavaFX UI using FXML and CSS

  
    Architecture Used

-MVC (Model–View–Controller)
Separates UI, logic, and data.

-DAO (Data Access Object)
Keeps all SQL queries separate from business logic.

-Service Layer
Handles validation and business rules before accessing the database.

    Database

-Uses SQLite

-Database file: sms.db

-Tables include:
  -users
  -students
  -courses
  -enrollments
  -grades


    User Interface

-Built using JavaFX

-UI layout created with FXML

-Styling applied using CSS

-Views are organized by feature:
 
  -Login
 
 -Dashboard
  
  -Student
 
  -Course
 
  -Enrollment
  
  -Grade

    User Roles

Admin: Full access to the system

Instructor: Manage courses, enrollments, and grades

Student: View enrollments, grades, and GPA
