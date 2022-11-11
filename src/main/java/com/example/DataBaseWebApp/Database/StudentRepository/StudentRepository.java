package com.example.DataBaseWebApp.Database.StudentRepository;

import com.example.DataBaseWebApp.Database.Entity.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.Nullable;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student, Long> {

    //@Query("select s from Student s where s.lastName = ?1")
    //List<Student> findByLastName(String name);

    //List<Student> findByDepartment(String department);//THIS MEANS: SELECT * FROM Student WHERE department=department

    //List<Student> findByFirstNameAndAgeAndDepartment(String firstName, int age, String department);
    List<Student> findByIdOrAgeOrLastNameOrFirstNameOrDepartmentOrEmailOrPhoneNumber(@Nullable long id, @Nullable int age, @Nullable String lastName,
                                                                                     @Nullable String firstName,
                                                                                     @Nullable String department, @Nullable String email,
                                                                                     @Nullable int phoneNumber);

    List<Student> findByIdOrAgeOrPhoneNumber(long id, int age, int phoneNumber);
    List<Student> findByLastNameOrFirstNameOrDepartmentOrEmail(String lastName, String firstName, String department, String email);
}
