package com.example.DataBaseWebApp.Database.Service;

import com.example.DataBaseWebApp.Database.Entity.Student;
import com.example.DataBaseWebApp.Database.StudentRepository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public String mainPage() {
        return "<form action=\"/student\" method=\"POST\">\n" +
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
                "<form action=\"/studentdelete\" method=\"GET\">" +
                "<input name=\"id\" placeholder=\"id\">\n" +
                "<button>Delete student with ID</button>" +
                "</form>" +
                "<form action=\"/studentfind\" method=\"GET\">" +
                "<input name=\"id\" placeholder=\"id\">\n" +
                "<button>Delete student with ID</button>" +
                "</form>";
    }

    @Override
    public void settingNewStudent(String firstName, String lastName, int age, String email, String department, int phoneNumber) {
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setAge(age);
        student.setEmail(email);
        student.setDepartment(department);
        student.setPhoneNumber(phoneNumber);
        studentRepository.save(student);
    }

    @Override
    public String printAllStudents() {
        Iterable<Student> student = studentRepository.findAll();
        String deleteLink  = "<a href=\"/studentdelete?id=" + student.getId() +"\">DELETE</a>\"";
        String updateLink = "<a href=\"/studentupdate?id=" + student.getId() +"\">UPDATE</a>\"";

        return "<form action=\"/add\" method=\"GET\">\n" +
                "<button>Back to the main page</button>\n" +
                "</form>" +
                "<table style=\"width:30%\"; text-align:center;> " +
                "<tr> " +
                "<th style=\"text-align: left\">ID:</th> " +
                "<th style=\"text-align: left\">Firstname:</th> " +
                "<th style=\"text-align: left\">Lastname:</th> " +
                "<th style=\"text-align: left\">Age:</th> " +
                "<th style=\"text-align: left\">E-mail:</th> " +
                "<th style=\"text-align: left\">Department:</th> " +
                "<th style=\"text-align: left\">Phone Number:</th> " +
                "</tr>" +
                "<tr>" +
                "<td style=\"text-align: left\">" + student.getId() + "</td>" +
                "<td style=\"text-align: left\">" + student.getFirstName() + "</td>" +
                "<td style=\"text-align: left\">" + student.getLastName() + "</td>" +
                "<td style=\"text-align: left\">" + student.getAge() + "</td>" +
                "<td style=\"text-align: left\">" + student.getEmail() + "</td>" +
                "<td style=\"text-align: left\">" + student.getDepartment() + "</td>" +
                "<td style=\"text-align: left\">" + student.getPhoneNumber() + "</td>" +
                "</tr>" +
                "<tr>" +
                "<td>" +deleteLink + "</td>" +
                "<td>" +updateLink + "</td>" +
                "</tr>";
    }
}
