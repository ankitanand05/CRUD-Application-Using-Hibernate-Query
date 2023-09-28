package com.hybernatejpa;

import com.hybernatejpa.dao.StudentDAO;
import com.hybernatejpa.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class HybernateJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(HybernateJpaApplication.class, args);
    }


    @Bean
    public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
        return runner->{
            //createStudent(studentDAO);

            //readStudent(studentDAO);

            //queryForStudent(studentDAO);

            //queryForStudentByLastName(studentDAO);
            
            //updateStudent(studentDAO);

            //deleteStudent(studentDAO);

            deleteAllStudents(studentDAO);




        };
    }

    private void deleteAllStudents(StudentDAO studentDAO) {
        System.out.println("Deleting All Student......");
        int numOfRowDeleted = studentDAO.deleteAllStudent();
        System.out.println("Deleted row: "+numOfRowDeleted);
    }

    private void deleteStudent(StudentDAO studentDAO) {
        int studentId = 4;

        System.out.println("Deleting Student Id: "+studentId);

        studentDAO.delete(studentId);
    }

    private void updateStudent(StudentDAO studentDAO) {

        //retrieve student  based on the id: primary key
        int studentId = 4 ;
        System.out.println("Getting student with id: "+ studentId);
        Student student = studentDAO.findById(studentId);


        System.out.println(student);
        //change the first name
        System.out.println("Updating Student....");
        student.setFirstName("Scooby");

        //update the student
        studentDAO.update(student);

        //display the updated student
        System.out.println("Updated Student: "+ student);
    }

    private void queryForStudentByLastName(StudentDAO studentDAO) {

        //get list of students
        List<Student> students = studentDAO.findByLastName("xyz");

        //display list of student
        for(Student student:students){
            System.out.println(student);
        }

        //

    }

    private void queryForStudent(StudentDAO studentDAO) {

        List<Student> theStudent = studentDAO.findAll();
        for(Student student:theStudent){
            System.out.println(student);
        }
    }

    private void readStudent(StudentDAO studentDAO) {
       System.out.println("Retrieving Student With Id: ===============>");
       Student s1 = studentDAO.findById(4);
        System.out.println("Found the Student: "+s1);
    }

    private void createStudent(StudentDAO studentDAO){
        //Create the object-------------------->
        System.out.println("Creating a new Student object ===================> ");
        Student s1 = new Student("ankit","anand","ankitanand@gmail.com");

        //Save the object --------------------->
        System.out.println("Saving the object ==================> ");
        studentDAO.save(s1);

        //Display Id of Save Student------------------------------------->
        System.out.println("Saved Student. Generated id: "+s1.getId());
    }
}
