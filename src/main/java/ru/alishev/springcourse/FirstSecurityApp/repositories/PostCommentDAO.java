package ru.alishev.springcourse.FirstSecurityApp.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.alishev.springcourse.FirstSecurityApp.models.Post;
import ru.alishev.springcourse.FirstSecurityApp.models.PostComment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Repository
public class PostCommentDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PostCommentDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }



    public List<PostComment> getAllCommentsByPostId(int postId) {
        String sql = "SELECT * FROM post_comments WHERE post_id = ?";
        return jdbcTemplate.query(sql, new PostCommentMapper(), postId);
    }






    public void updateComment(PostComment postComment) {
        String sql = "UPDATE post_comments SET post_comment_user = ?, post_comment_text = ? WHERE post_comment_id = ?";
        jdbcTemplate.update(sql, postComment.getPostCommentUserEmail(), postComment.getPostCommentText(),
                postComment.getPostCommentId());
    }


    public void save(PostComment postComment) {
        System.out.println(postComment.getPostCommentUserEmail());
        String sql = "INSERT INTO post_comments (post_id, post_comment_user, post_comment_text) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, postComment.getPostId(), postComment.getPostCommentUserEmail(),
                postComment.getPostCommentText());
    }

    public int deleteCommentById(int postCommentId) {
        String sql = "DELETE FROM post_comments WHERE post_comment_id = ?";
        return jdbcTemplate.update(sql, postCommentId);
    }

    private static class PostCommentMapper implements RowMapper<PostComment> {
        @Override
        public PostComment mapRow(ResultSet rs, int rowNum) throws SQLException {
            PostComment postComment = new PostComment(rs.getInt("post_id"), rs.getString("post_comment_user"),
                    rs.getString("post_comment_text"));
            postComment.setPostCommentId(rs.getInt("post_comment_id"));
            postComment.setPostCommentCreatedTime(rs.getTimestamp("post_comment_created_time"));
            return postComment;
        }
    }

}

