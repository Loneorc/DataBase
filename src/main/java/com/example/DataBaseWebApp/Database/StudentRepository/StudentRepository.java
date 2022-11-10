package com.example.DataBaseWebApp.Database.StudentRepository;

import com.example.DataBaseWebApp.Database.Entity.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long> {
}
