package ru.alishev.springcourse.FirstSecurityApp.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class UserProfile {
    private int id;
    @NotEmpty(message = "email should not be empty")
    @Size(min = 2, max = 100, message = "Length of email should be from 2 char to 100")
    private String email;

    @NotEmpty(message = "password should not be empty")
    @Size(min = 2, max = 100, message = "Length of password should be from 2 char to 100")
    private String password;
    private String country;
    private LocalDate dateOfBirth;
    private String name;
    private String surname;

    private String role;

    public UserProfile() {}

    public UserProfile(String email, String password, String country, LocalDate dateOfBirth, String name, String surname) {
        this.email = email;
        this.password = password;
        this.country = country;
        this.dateOfBirth = dateOfBirth;
        this.name = name;
        this.surname = surname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", country='" + country + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
