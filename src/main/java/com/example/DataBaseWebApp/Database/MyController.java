package com.example.DataBaseWebApp.Database;

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


        Iterable<Student> allStudents = studentRepository.findAll();

        return "<form action=\"/add\" method=\"GET\">\n" +
                "<button>Back to the main page</button>\n" +
                "</form>" +
                studentRepository.findAll().toString();


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
    public ResponseEntity<String>  updateStudent(@RequestParam long id, String firstname){

String test = "Test";
        return
        ResponseEntity.ok("<form action=\"/student\" method=\"POST\">\n" +
                "<input name=\"firstName\" placeholder= " + studentRepository.findById(id).get().getFirstName() + " >\n" +
                "<input name=\"lastName\" placeholder= " + studentRepository.findById(id).get().getLastName() + ">\n" +
                "<input name=\"age\" placeholder= " + studentRepository.findById(id).get().getAge() + " >\n"+
                "<input name=\"email\" placeholder= " + studentRepository.findById(id).get().getEmail() + " >\n"+
                "<input name=\"department\" placeholder= " + studentRepository.findById(id).get().getDepartment() + " >\n"+
                "<input name=\"phoneNumber\" placeholder= " + studentRepository.findById(id).get().getPhoneNumber() + " >\n"+
                "<button>SET</button>\n" +
                "</form>" +

                studentRepository.findById(id).toString());

    }

}
