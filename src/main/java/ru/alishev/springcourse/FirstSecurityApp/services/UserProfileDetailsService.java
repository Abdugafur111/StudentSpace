package ru.alishev.springcourse.FirstSecurityApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.alishev.springcourse.FirstSecurityApp.models.UserProfile;
import ru.alishev.springcourse.FirstSecurityApp.repositories.UserProfileDAO;
import ru.alishev.springcourse.FirstSecurityApp.security.UserProfileDetails;
import java.util.Optional;

@Service
public class UserProfileDetailsService implements UserDetailsService {

    private final UserProfileDAO userProfileDAO;

    @Autowired
    public UserProfileDetailsService(UserProfileDAO userProfileDAO) {
        this.userProfileDAO = userProfileDAO;
    }
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<UserProfile> person = Optional.ofNullable(userProfileDAO.getUserByEmail(s));
        System.out.println("UserProfile");

        if (person.isEmpty())
            throw new UsernameNotFoundException("User not found");

        return new UserProfileDetails(person.get());

    }
}
