package ru.alishev.springcourse.FirstSecurityApp.models;

public class Follower {
    private String studentEmail;
    private String followerEmail;

    public Follower() {
    }

    public Follower(String studentEmail, String followerEmail) {
        this.studentEmail = studentEmail;
        this.followerEmail = followerEmail;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getFollowerEmail() {
        return followerEmail;
    }

    public void setFollowerEmail(String followerEmail) {
        this.followerEmail = followerEmail;
    }
}
