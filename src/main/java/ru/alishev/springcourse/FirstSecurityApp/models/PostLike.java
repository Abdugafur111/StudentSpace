package ru.alishev.springcourse.FirstSecurityApp.models;

import java.sql.Timestamp;

public class PostLike {
    private int postId;
    private String postLikedUser;
    private Timestamp postLikesCreatedTime;

    public PostLike() {}

    public PostLike(int postId, String postLikedUser, Timestamp postLikesCreatedTime) {
        this.postId = postId;
        this.postLikedUser = postLikedUser;
        this.postLikesCreatedTime = postLikesCreatedTime;
    }

    // getters and setters
    public int getPostId() {
        return postId;
    }
    public void setPostId(int postId) {
        this.postId = postId;
    }
    public String getPostLikedUser() {
        return postLikedUser;
    }
    public void setPostLikedUser(String postLikedUser) {
        this.postLikedUser = postLikedUser;
    }
    public Timestamp getPostLikesCreatedTime() {
        return postLikesCreatedTime;
    }
    public void setPostLikesCreatedTime(Timestamp postLikesCreatedTime) {
        this.postLikesCreatedTime = postLikesCreatedTime;
    }
}
