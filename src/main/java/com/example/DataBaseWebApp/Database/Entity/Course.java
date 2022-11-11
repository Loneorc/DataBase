//package com.example.DataBaseWebApp.Database.Entity;
//
//import org.hibernate.mapping.Set;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name="course")
//public class Course {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "id", nullable = false)
//    private Long id;
//    private String courseName;
//
//    //@OneToMany(mappedBy="course")
//    //private Set<Student> students;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getCourseName() {
//        return courseName;
//    }
//
//    public void setCourseName(String courseName) {
//        this.courseName = courseName;
//    }
//}
