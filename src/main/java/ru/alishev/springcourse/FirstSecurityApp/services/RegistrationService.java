package ru.alishev.springcourse.FirstSecurityApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alishev.springcourse.FirstSecurityApp.models.UserProfile;
import ru.alishev.springcourse.FirstSecurityApp.repositories.UserProfileDAO;


@Service
public class RegistrationService {

    private final UserProfileDAO userProfileDAO;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(UserProfileDAO userProfileDAO, PasswordEncoder passwordEncoder) {
        this.userProfileDAO = userProfileDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(UserProfile userProfile) {
        userProfile.setPassword(passwordEncoder.encode(userProfile.getPassword()));
        System.out.println(passwordEncoder.encode(userProfile.getPassword()));
        userProfileDAO.addUser(userProfile);
    }
}
