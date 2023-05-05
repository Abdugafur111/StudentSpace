package ru.alishev.springcourse.FirstSecurityApp.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;


public class UserProfile {



    @NotEmpty(message = "email should not be empty")
    @Size(min = 2, max = 100, message = "Length of email should be from 2 char to 100")
    private String email;

    @NotEmpty(message = "password should not be empty")
    @Size(min = 2, max = 100, message = "Length of password should be from 2 char to 100")
    private String password;

    private String address;
    private String dateOfBirth;
    private String firstName;
    private String lastName;

    private String role;

    private int studentId;

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public UserProfile(String email, String password, String address, String dateOfBirth, String firstName, String lastName, int studentId) {
        this.email = email;
        this.password = password;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentId = studentId;
    }

    public UserProfile() {}


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", country='" + address + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", name='" + firstName + '\'' +
                ", surname='" + firstName + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
