package ru.alishev.springcourse.FirstSecurityApp.models;

import java.time.LocalDate;

public class Post {
    private int postId;
    private String email;
    private String content;
    private LocalDate postCreatedTime;

    // constructors
    public Post() {}

    public Post(String email, String content) {
        this.email = email;
        this.content = content;
    }

    // getters and setters
    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getPostCreatedTime() {
        return postCreatedTime;
    }

    public void setPostCreatedTime(LocalDate postCreatedTime) {
        this.postCreatedTime = postCreatedTime;
    }
}
