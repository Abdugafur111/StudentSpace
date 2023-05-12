package ru.alishev.springcourse.FirstSecurityApp.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.alishev.springcourse.FirstSecurityApp.models.Post;

@Component
public class PostDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PostDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Post> getAllPosts() {
        return jdbcTemplate.query("SELECT * FROM POSTS ORDER BY post_created_time DESC", new PostRowMapper());
    }


    public Post getPostById(int id) {
        return jdbcTemplate.query("SELECT * FROM POSTS WHERE post_id=?", new Object[]{id}, new PostResultSetExtractor());
    }

    public void addPost(Post post) {
        jdbcTemplate.update("INSERT INTO POSTS(email, content) VALUES (?, ?)", post.getEmail(), post.getContent());
    }

    public void updatePost(int id, Post updatedPost) {
        jdbcTemplate.update("UPDATE POSTS SET email=?, content=? WHERE post_id=?", updatedPost.getEmail(), updatedPost.getContent(), id);
    }

    public void deletePost(int id) {
        jdbcTemplate.update("DELETE FROM POSTS WHERE post_id=?", id);
    }

    public List<Post> getAllPostsByEmail(String email) {
        return jdbcTemplate.query("SELECT * FROM POSTS WHERE email=? ORDER BY post_created_time DESC",
                new Object[]{email}, new PostRowMapper());
    }


    private static final class PostRowMapper implements RowMapper<Post> {

        @Override
        public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
            Post post = new Post();
            post.setPostId(rs.getInt("post_id"));
            post.setEmail(rs.getString("email"));
            post.setContent(rs.getString("content"));
            post.setPostCreatedTime(LocalDate.from(rs.getTimestamp("post_created_time").toLocalDateTime()));
            return post;
        }
    }

    private static final class PostResultSetExtractor implements ResultSetExtractor<Post> {

        @Override
        public Post extractData(ResultSet rs) throws SQLException, DataAccessException {
            if (rs.next()) {
                Post post = new Post();
                post.setPostId(rs.getInt("post_id"));
                post.setEmail(rs.getString("email"));
                post.setContent(rs.getString("content"));
                post.setPostCreatedTime(LocalDate.from(rs.getTimestamp("post_created_time").toLocalDateTime()));
                return post;
            } else {
                return null;
            }
        }
    }
}
