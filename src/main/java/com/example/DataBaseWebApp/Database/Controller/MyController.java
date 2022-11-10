package com.example.DataBaseWebApp.Database.Controller;

import com.example.DataBaseWebApp.Database.Entity.Student;
import com.example.DataBaseWebApp.Database.Service.StudentService;
import com.example.DataBaseWebApp.Database.StudentRepository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;

@Controller
public class MyController {

    private StudentRepository studentRepository;
    private StudentService studentService;
    @Autowired
    public MyController(StudentRepository studentRepository, StudentService studentService) {
        this.studentRepository = studentRepository;
        this.studentService = studentService;
    }

    @GetMapping("/add")
    @ResponseBody
    public String addsStudent() {

        return  studentService.mainPage();
    }

    @PostMapping("/student")
    @ResponseBody
    public String printStudent(@RequestParam String firstName, String lastName, int age,
                               String email, String department, int phoneNumber){

       studentService.settingNewStudent( firstName, lastName, age, email, department, phoneNumber);

       return  "Student Added!" + "<form action=\"/add\" method=\"GET\">\n" +
               "<button>Back - Add another student</button>\n" +
               "</form>";
    }

    @GetMapping("/studentcheck")
    @ResponseBody
    public String getStudent(){


        //Iterable<Student> allStudents = studentRepository.findAll();

        return studentService.printAllStudents();


    }

    @GetMapping("/studentdelete")
    @ResponseBody
    public String deleteStudent(@RequestParam long id){

        studentRepository.deleteAllById(Collections.singleton(id));

        return "<form action=\"/add\" method=\"GET\">\n" +
                "<button>Back to the main page</button>\n" +
                "</form>" +
                "Student with " + id + " was deleted!\n"
                + studentRepository.findAll().toString();
    }

    @GetMapping("/studentupdate")
    @ResponseBody
    public ResponseEntity<String>  updateStudent(@RequestParam long id){


        Student student = studentRepository.findById(id).get();
        //String updateLink = "<a href= 'studentupdatedone?id='+ id + "&firstname=" + student.getFirstName() + "\">UPDATE</a>\"";

        return
        ResponseEntity.ok("<form action='/studentupdatedone' method='POST'>\n" +
                "<input name='id' placeholder= " + student.getId() + " >\n" +
                "<input name='firstName' placeholder= " + student.getFirstName() + " >\n" +
                "<input name='lastName' placeholder= " + student.getLastName() + ">\n" +
                "<input name='age' placeholder= " + student.getAge() + " >\n"+
                "<input name='email' placeholder= " + student.getEmail() + " >\n"+
                "<input name='department' placeholder= " + student.getDepartment() + " >\n"+
                "<input name='phoneNumber' placeholder= " + student.getPhoneNumber() + " >\n"+
                "<button>SET</button>\n" +
                "</form>" +

                studentRepository.findById(id));
    }


    @PostMapping("/studentupdatedone")
    @ResponseBody
    public String updateDoneStudent(@RequestParam long id, String firstName, String lastName, int age,
                               String email, String department, int phoneNumber){

        /*studentRepository.findById(id).get().setFirstName(firstName);
        studentRepository.findById(id).get().setLastName(lastName);
        studentRepository.findById(id).get().setAge(age);
        studentRepository.findById(id).get().setEmail(email);
        studentRepository.findById(id).get().setDepartment(department);
        studentRepository.findById(id).get().setPhoneNumber(phoneNumber);*/

Student studentUpdate= studentRepository.findById(id).get();

        //Student studentUpdate = new Student();
        studentUpdate.setFirstName(firstName);
        studentUpdate.setLastName(lastName);
        studentUpdate.setAge(age);
        studentUpdate.setEmail(email);
        studentUpdate.setDepartment(department);
        studentUpdate.setPhoneNumber(phoneNumber);
        studentRepository.save(studentUpdate);



        return  "Student Updated!!" + "<form action=\"/add\" method=\"GET\">\n" +
                "<button>Back to main page</button>\n" +
                "</form>";
    }




}
