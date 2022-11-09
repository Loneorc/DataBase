package com.example.DataBaseWebApp.Database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class MyController {

    private StudentRepository studentRepository;

    @Autowired
    public MyController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/add")
    @ResponseBody
    public ResponseEntity<String> addsStudent() {



        return ResponseEntity.ok("<form action=\"/student\" method=\"POST\">\n" +
                "<input name=\"firstName\" placeholder=\"Your firstname\">\n" +
                "<input name=\"lastName\" placeholder=\"lastName\">\n" +
                "<input name=\"age\" placeholder=\"age\">\n"+
                "<input name=\"email\" placeholder=\"email\">\n"+
                "<input name=\"department\" placeholder=\"department\">\n"+
                "<input name=\"phoneNumber\" placeholder=\"phoneNumber\">\n"+
                "<button>Go</button>\n" +
                "</form>" +
                 "<form action=\"/studentcheck\" method=\"GET\">" +
                "<button>Print all students</button>" +
                "</form>" +
                "<form action=\"/studentdelete\" method=\"POST\">" +
                "<input name=\"id\" placeholder=\"id\">\n" +
                "<button>Delete student with ID</button>" +
                "</form>"
                );
    }

    @PostMapping("/student")
    @ResponseBody
    public String printStudent(@RequestParam String firstName, String lastName, int age,
                               String email, String department, int phoneNumber){

        //List<Student> students = new ArrayList<>();

        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setAge(age);
        student.setEmail(email);
        student.setDepartment(department);
        student.setPhoneNumber(phoneNumber);
        studentRepository.save(student);

        //students.add(student);

       return  "Student Added!" + "<form action=\"/add\" method=\"GET\">\n" +
               "<button>Back - Add another student</button>\n" +
               "</form>";
    }

    @GetMapping("/studentcheck")
    @ResponseBody
    public String getStudent(){
        return "<form action=\"/add\" method=\"GET\">\n" +
                "<button>Back to the main page</button>\n" +
                "</form>" +
                studentRepository.findAll().toString();
    }

    @PostMapping("/studentdelete")
    @ResponseBody
    public String deleteStudent(long id){
        studentRepository.deleteAllById(Collections.singleton(id));


        return "<form action=\"/add\" method=\"GET\">\n" +
                "<button>Back to the main page</button>\n" +
                "</form>" +
                "Student with " + id + " was deleted!\n"
                + studentRepository.findAll().toString();
    }



}
