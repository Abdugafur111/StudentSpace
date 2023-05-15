package ru.alishev.springcourse.FirstSecurityApp.repositories;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.alishev.springcourse.FirstSecurityApp.models.UserProfile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class UserProfileDAO {

    private final JdbcTemplate jdbcTemplate;
    private final PasswordEncoder passwordEncoder;


    public UserProfileDAO(JdbcTemplate jdbcTemplate, PasswordEncoder passwordEncoder) {
        this.jdbcTemplate = jdbcTemplate;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserProfile> getAllUsers() {
        return jdbcTemplate.query("SELECT * FROM USERS", new BeanPropertyRowMapper<>(UserProfile.class));
    }

    public UserProfile getUserById(int id) {
       String studentId = String.valueOf(id);

        return jdbcTemplate.query("SELECT * FROM USERS WHERE student_id=?", new Object[]{studentId}, new BeanPropertyRowMapper<>(UserProfile.class))
                .stream().findAny().orElse(null);
    }

    public void addUser(UserProfile user) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println(user.getDateOfBirth());
        LocalDate dateOfBirth = LocalDate.parse(user.getDateOfBirth(), formatter);
        jdbcTemplate.update("INSERT INTO USERS(email,student_id, password, address, date_of_birth, first_name, last_name, role) VALUES (?,?, ?, ?, ?, ?, ?,'ROLE_STUDENT')",
                user.getEmail(),user.getStudentId(), user.getPassword(), user.getAddress(), dateOfBirth, user.getFirstName(), user.getLastName());

    }

    public void updateUser(int id, UserProfile updatedUser) {
        String studentId = String.valueOf(id);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println(updatedUser.getDateOfBirth());
        LocalDate dateOfBirth = LocalDate.parse(updatedUser.getDateOfBirth(), formatter);
        updatedUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        jdbcTemplate.update("UPDATE USERS SET email=?, password=?, address=?, date_of_birth=?, first_name=?, last_name=? WHERE student_id=?",
                updatedUser.getEmail(), updatedUser.getPassword(), updatedUser.getAddress(), dateOfBirth, updatedUser.getFirstName(), updatedUser.getLastName(), studentId);

    }

    public void deleteUser(int id) {
        String studentId = String.valueOf(id);

        jdbcTemplate.update("DELETE FROM USERS WHERE student_id=?", studentId);
    }

    public UserProfile getUserByEmail(String email) {
        return jdbcTemplate.query("SELECT * FROM USERS WHERE email=?", new Object[]{email}, new BeanPropertyRowMapper<>(UserProfile.class))
                .stream().findAny().orElse(null);
    }


}
