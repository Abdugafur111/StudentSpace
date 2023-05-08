package ru.alishev.springcourse.FirstSecurityApp.models;

import java.util.Date;

public class PostComment {
    private int postId;
    private int postCommentId;
    private String postCommentUserEmail;
    private String postCommentText;
    private Date postCommentCreatedTime;

    // Constructor
    public PostComment(int postId, String postCommentUser, String postCommentText) {
        this.postId = postId;
        this.postCommentUserEmail = postCommentUser;
        this.postCommentText = postCommentText;
    }

    // Getters and Setters
    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getPostCommentId() {
        return postCommentId;
    }

    public void setPostCommentId(int postCommentId) {
        this.postCommentId = postCommentId;
    }

    public String getPostCommentUserEmail() {
        return postCommentUserEmail;
    }

    public void setPostCommentUserEmail(String postCommentUserEmail) {
        this.postCommentUserEmail = postCommentUserEmail;
    }

    public String getPostCommentText() {
        return postCommentText;
    }

    public void setPostCommentText(String postCommentText) {
        this.postCommentText = postCommentText;
    }

    public Date getPostCommentCreatedTime() {
        return postCommentCreatedTime;
    }

    public void setPostCommentCreatedTime(Date postCommentCreatedTime) {
        this.postCommentCreatedTime = postCommentCreatedTime;
    }
}
