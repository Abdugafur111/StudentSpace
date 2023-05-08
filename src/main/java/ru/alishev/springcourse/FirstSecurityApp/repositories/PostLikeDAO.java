package ru.alishev.springcourse.FirstSecurityApp.repositories;

import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.alishev.springcourse.FirstSecurityApp.models.PostLike;

@Repository
public class PostLikeDAO {


    private JdbcTemplate jdbcTemplate;
    @Autowired
    public PostLikeDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addPostLike(int postId, String likedUser) {
        String sql = "INSERT INTO post_likes(post_id, post_liked_user) VALUES(?, ?)";
        jdbcTemplate.update(sql, postId, likedUser);
    }

    public void deletePostLike(int postId, String likedUser) {
        String sql = "DELETE FROM post_likes WHERE post_id = ? AND post_liked_user = ?";
        jdbcTemplate.update(sql, postId, likedUser);
    }



    public  List<PostLike> getPostLikes(int postId) {
        String sql = "SELECT * FROM post_likes WHERE post_id = ?";
        return jdbcTemplate.query(sql, new Object[] {postId}, (rs, rowNum) -> {
            int id = rs.getInt("post_id");
            String likedUser = rs.getString("post_liked_user");
            Timestamp createdTime = rs.getTimestamp("post_likes_created_time");
            return new PostLike(id, likedUser, createdTime);
        });
    }

    public boolean getPostLikeByEmail(int postId, String email) {
        String sql = "SELECT COUNT(*) FROM post_likes WHERE post_id = ? AND post_liked_user = ?";
        int count = jdbcTemplate.queryForObject(sql, new Object[] {postId, email}, Integer.class);
        return count > 0;
    }

}

