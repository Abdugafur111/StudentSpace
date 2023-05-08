package ru.alishev.springcourse.FirstSecurityApp.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FollowersDAO {

    private final JdbcTemplate jdbcTemplate;


    @Autowired
    public FollowersDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addFollower(String studentEmail, String followerEmail) {
        String sql = "INSERT INTO FOLLOWERS (student_email, follower_email) VALUES (?, ?)";
        jdbcTemplate.update(sql, studentEmail, followerEmail);
    }

    public void removeFollower(String studentEmail, String followerEmail) {
        String sql = "DELETE FROM FOLLOWERS WHERE student_email = ? AND follower_email = ?";
        jdbcTemplate.update(sql, studentEmail, followerEmail);
    }

    public List<String> getFollowers(String studentEmail) {
        String sql = "SELECT follower_email FROM FOLLOWERS WHERE student_email = ?";
        return jdbcTemplate.queryForList(sql, String.class, studentEmail);
    }

    public List<String> getFollowing(String followerEmail) {
        String sql = "SELECT student_email FROM FOLLOWERS WHERE follower_email = ?";
        return jdbcTemplate.queryForList(sql, String.class, followerEmail);
    }

    public void removeFollowing(String followerEmail, String studentEmail) {
        String sql = "DELETE FROM FOLLOWERS WHERE follower_email = ? AND student_email = ?";
        jdbcTemplate.update(sql, followerEmail, studentEmail);
    }
}

