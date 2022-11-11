package com.example.DataBaseWebApp.Database.Controller;

import com.example.DataBaseWebApp.Database.Entity.Student;
import com.example.DataBaseWebApp.Database.Service.StudentService;
import com.example.DataBaseWebApp.Database.StudentRepository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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

        return studentService.mainPage();
    }

    @PostMapping("/student")
    @ResponseBody
    public String printStudent(@RequestParam String firstName, String lastName, int age,
                               String email, String department, int phoneNumber) {

        return studentService.settingNewStudent(firstName, lastName, age, email, department, phoneNumber);
    }

    @GetMapping("/studentcheck")
    @ResponseBody
    public String getStudent() {
        return studentService.printAllStudents();
    }

    @GetMapping("/studentdelete")
    @ResponseBody
    public String deleteStudent(@RequestParam long id) {
        return studentService.deleteForm(id);
    }

    @GetMapping("/studentupdate")
    @ResponseBody
    public String updateStudent(@RequestParam long id) {
        return studentService.updateForm(id);
    }


    @PostMapping("/studentupdatedone")
    @ResponseBody
    public String updateDoneStudent(@RequestParam long id, String firstName, String lastName, int age,
                                    String email, String department, int phoneNumber) {

        return studentService.updateDoneForm(id, firstName, lastName, age, email, department, phoneNumber);
    }

    @PostMapping("/studentfind")
    @ResponseBody
    public String findStudent(@RequestParam String lastName) {

        boolean isNumeric = lastName.chars().allMatch( Character::isDigit );
        String returnValue;

        if (isNumeric){
            returnValue = studentRepository.findByIdOrAgeOrPhoneNumber(Long.parseLong(lastName), Integer.parseInt(lastName), Integer.parseInt(lastName)).toString();
            if(returnValue.isBlank()){
                return "There is no student with that attribute!" + "<form action=\"/add\" method=\"GET\">\n" +
                        "<button>Back to main page</button>\n" +
                        "</form>";
            } else {
                return returnValue;
            }
        } else  {

            returnValue = studentRepository.findByLastNameOrFirstNameOrDepartmentOrEmail(lastName, lastName, lastName, lastName).toString();
            if(returnValue.isBlank()){
                return "There is no student with that attribute!" + "<form action=\"/add\" method=\"GET\">\n" +
                        "<button>Back to main page</button>\n" +
                        "</form>";
            } else {
                return returnValue;
            }
        }
    }

    // This is here but not used! Forget it!
    @PostMapping("/studentsearch")
    @ResponseBody
    public String findStudentByEverything(@RequestParam String lastName) {
        boolean isNumeric = lastName.chars().allMatch( Character::isDigit );

        if (isNumeric){
            return studentRepository.findByIdOrAgeOrPhoneNumber(Long.parseLong(lastName), Integer.parseInt(lastName), Integer.parseInt(lastName)).toString();
        }
        return studentRepository.findByLastNameOrFirstNameOrDepartmentOrEmail(lastName, lastName, lastName, lastName).toString();
    }
}




