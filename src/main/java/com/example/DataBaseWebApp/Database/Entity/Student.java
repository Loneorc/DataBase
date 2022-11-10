package com.example.DataBaseWebApp.Database.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Student {

    @Id
    @GeneratedValue
    private long id;
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private String department;
    private int phoneNumber;

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String name) {
        this.firstName = name;
    }

    public long getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        String deleteLink  = "<a href=\"/studentdelete?id=" + id +"\">DELETE</a>\"";
        String updateLink = "<a href=\"/studentupdate?id=" + id +"\">UPDATE</a>\"";

        /*return "<h2>Student " + id + "</h2>"+
                "<ul> " +
                "<li>" + "Firstname:    " + firstName + "</li> " +
                "<li>" + "Lastname:     " + lastName + "</li> " +
                "<li>" + "Age:          " + age + "</li> " +
                "<li>" + "E-mail:       " + email + "</li> " +
                "<li>" + "Department:   " + department + "</li> " +
                "<li>" + "Phone Number: " + phoneNumber + "</li> " +
                "</ul>" +
                deleteLink + "\t" + updateLink;*/

        return
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
            "<td style=\"text-align: left\">" + id + "</td>" +
            "<td style=\"text-align: left\">" + firstName + "</td>" +
            "<td style=\"text-align: left\">" + lastName + "</td>" +
            "<td style=\"text-align: left\">" + age + "</td>" +
            "<td style=\"text-align: left\">" + email + "</td>" +
            "<td style=\"text-align: left\">" + department + "</td>" +
            "<td style=\"text-align: left\">" + phoneNumber + "</td>" +
        "</tr>" +
        "<tr>" +
            "<td>" +deleteLink + "</td>" +
            "<td>" +updateLink + "</td>" +
        "</tr>";
    }


}
