package com.example.DataBaseWebApp.Database.StudentRepository;

import com.example.DataBaseWebApp.Database.Entity.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student, Long> {

    //@Query("select s from Student s where s.lastName = ?1")
    //List<Student> findByLastName(String name);

    //List<Student> findByDepartment(String department);//THIS MEANS: SELECT * FROM Student WHERE department=department

    //List<Student> findByFirstNameAndAgeAndDepartment(String firstName, int age, String department);
    List<Student> findByIdOrAgeOrLastNameOrFirstNameOrDepartmentOrEmailOrPhoneNumber(long id, int age, String lastName, String firstName,
                                String department, String email, int phoneNumber);

    List<Student> findByIdOrAgeOrPhoneNumber(long id, int age, int phoneNumber);
    List<Student> findByLastNameOrFirstNameOrDepartmentOrEmail(String lastName, String firstName, String department, String email);
}
