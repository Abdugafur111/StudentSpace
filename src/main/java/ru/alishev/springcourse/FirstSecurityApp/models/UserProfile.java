package ru.alishev.springcourse.FirstSecurityApp.models;


import ru.alishev.springcourse.FirstSecurityApp.util.StudentIdValidator;

import javax.validation.Constraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.time.LocalDate;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.constraints.Email;


public class UserProfile {



    @NotEmpty(message = "email should not be empty")
    @Size(min = 2, max = 100, message = "Length of email should be from 2 char to 100")
    @Email(message = "Invalid email format")
    private String email;

    @NotEmpty(message = "password should not be empty")
    @Size(min = 2, max = 100, message = "Length of password should be from 2 char to 100")
    private String password;
    @NotEmpty(message = "password should not be empty")
    private String address;
    private String dateOfBirth;

    @Size(min = 2, max = 30, message = "Length of firstname should be from 2 char to 100")
    private String firstName;
    @Size(min = 2, max = 30, message = "Length of second name should be from 2 char to 100")
    private String lastName;

    private boolean followed;

    public boolean isFollowed() {
        return followed;
    }

    public void setFollowed(boolean follow) {
        this.followed = follow;
    }

    private String role;
    @StudentIdConstraint
    private long studentId;

    @Constraint(validatedBy = StudentIdValidator.class)
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface StudentIdConstraint {
        String message() default "Student ID should be exactly 7 digits";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public UserProfile(String email, String password, String address, String dateOfBirth, String firstName, String lastName, long studentId) {
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
