package com.example.DataBaseWebApp.Database.Service;

public interface StudentService {


    String mainPage ();
    String settingNewStudent (String firstName, String lastName, int age,
                            String email, String department, int phoneNumber);
    String printAllStudents ();
    String deleteForm(long id);
    String updateForm(long id);
    String updateDoneForm(long id, String firstName, String lastName, int age,
                          String email, String department, int phoneNumber);
    String findStudentForm(String lastName);
}
