package com.example.DataBaseWebApp.Database.Service;

public interface StudentService {


    String mainPage ();
    void settingNewStudent (String firstName, String lastName, int age,
                            String email, String department, int phoneNumber);

    String printAllStudents ();
}
