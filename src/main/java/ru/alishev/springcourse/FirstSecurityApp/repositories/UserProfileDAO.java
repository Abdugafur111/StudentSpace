package ru.alishev.springcourse.FirstSecurityApp.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.alishev.springcourse.FirstSecurityApp.models.UserProfile;
import java.util.List;

@Component
public class UserProfileDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserProfileDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<UserProfile> getAllUsers() {
        return jdbcTemplate.query("SELECT * FROM User_Profiles", new BeanPropertyRowMapper<>(UserProfile.class));
    }

    public UserProfile getUserById(int id) {
        return jdbcTemplate.query("SELECT * FROM User_Profiles WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(UserProfile.class))
                .stream().findAny().orElse(null);
    }

    public void addUser(UserProfile user) {
        jdbcTemplate.update("INSERT INTO User_Profiles(email, password, country, date_of_birth, name, surname, role) VALUES (?, ?, ?, ?, ?, ?,'ROLE_USER')",
                user.getEmail(), user.getPassword(), user.getCountry(), user.getDateOfBirth(), user.getName(), user.getSurname());
    }

    public void updateUser(int id, UserProfile updatedUser) {
        jdbcTemplate.update("UPDATE User_Profiles SET email=?, password=?, country=?, date_of_birth=?, name=?, surname=? WHERE id=?",
                updatedUser.getEmail(), updatedUser.getPassword(), updatedUser.getCountry(), updatedUser.getDateOfBirth(), updatedUser.getName(), updatedUser.getSurname(), id);
    }

    public void deleteUser(int id) {
        jdbcTemplate.update("DELETE FROM User_Profiles WHERE id=?", id);
    }

    public UserProfile getUserByEmail(String email) {
        return jdbcTemplate.query("SELECT * FROM User_Profiles WHERE email=?", new Object[]{email}, new BeanPropertyRowMapper<>(UserProfile.class))
                .stream().findAny().orElse(null);
    }


}
