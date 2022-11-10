package com.example.DataBaseWebApp.Database.Service;

import com.example.DataBaseWebApp.Database.Entity.Student;
import com.example.DataBaseWebApp.Database.StudentRepository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;

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
                "<form action=\"/studentfind\" method=\"POST\">" +
                "<input name=\"lastName\" placeholder=\"Last Name\">\n" +
                "<button>Find student by last name.</button>" +
                "</form>";
    }

    @Override
    public String settingNewStudent(String firstName, String lastName, int age, String email, String department, int phoneNumber) {
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setAge(age);
        student.setEmail(email);
        student.setDepartment(department);
        student.setPhoneNumber(phoneNumber);
        studentRepository.save(student);

        return "Student Added!" + "<form action=\"/add\" method=\"GET\">\n" +
                "<button>Back - Add another student</button>\n" +
                "</form>";
    }

    @Override
    public String printAllStudents() {

        Iterable<Student> students = studentRepository.findAll();

        for (Student student : students) {
            String deleteLink  = "<a href=\"/studentdelete?id=" + student.getId() +"\">DELETE</a>\"";
            String updateLink = "<a href=\"/studentupdate?id=" + student.getId() +"\">UPDATE</a>\"";
        }
        return students.toString();
    }

    @Override
    public String deleteForm(long id) {

        studentRepository.deleteAllById(Collections.singleton(id));
        return "<form action=\"/add\" method=\"GET\">\n" +
                "<button>Back to the main page</button>\n" +
                "</form>" +
                "Student with " + id + " was deleted!\n"
                + studentRepository.findAll().toString();
    }

    @Override
    public String updateForm(long id) {
        Student student = studentRepository.findById(id).get();

        return "<form action='/studentupdatedone' method='POST'>\n" +
                "<input name='id' placeholder= " + student.getId() + " >\n" +
                "<input name='firstName' placeholder= " + student.getFirstName() + " >\n" +
                "<input name='lastName' placeholder= " + student.getLastName() + ">\n" +
                "<input name='age' placeholder= " + student.getAge() + " >\n"+
                "<input name='email' placeholder= " + student.getEmail() + " >\n"+
                "<input name='department' placeholder= " + student.getDepartment() + " >\n"+
                "<input name='phoneNumber' placeholder= " + student.getPhoneNumber() + " >\n"+
                "<button>SET</button>\n" +
                "</form>" +
                studentRepository.findById(id);
    }

    @Override
    public String updateDoneForm(long id, String firstName, String lastName, int age, String email, String department, int phoneNumber) {

        Student studentUpdate= studentRepository.findById(id).get();

        studentUpdate.setFirstName(firstName);
        studentUpdate.setLastName(lastName);
        studentUpdate.setAge(age);
        studentUpdate.setEmail(email);
        studentUpdate.setDepartment(department);
        studentUpdate.setPhoneNumber(phoneNumber);
        studentRepository.save(studentUpdate);

        return "Student Updated!!" + "<form action=\"/add\" method=\"GET\">\n" +
                "<button>Back to main page</button>\n" +
                "</form>";
    }

    @Override
    public String findStudentForm(String lastName) {
        Iterable<Student> students = studentRepository.findAll();

        for (Student student : students) {
            if (student.getLastName().equals(lastName)){
                return student.toString() +
                        "<form action=\"/add\" method=\"GET\">\n" +
                        "<button>Back to main page</button>\n" +
                        "</form>";
            }
        }
        return "There is no student with that name!" + "<form action=\"/add\" method=\"GET\">\n" +
                "<button>Back to main page</button>\n" +
                "</form>";
    }
}
