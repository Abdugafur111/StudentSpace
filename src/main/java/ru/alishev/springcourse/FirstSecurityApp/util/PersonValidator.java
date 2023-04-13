package ru.alishev.springcourse.FirstSecurityApp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.alishev.springcourse.FirstSecurityApp.models.UserProfile;
import ru.alishev.springcourse.FirstSecurityApp.services.UserProfileDetailsService;

@Component
public class PersonValidator implements Validator {

    private final UserProfileDetailsService personDetailsService;

    @Autowired
    public PersonValidator(UserProfileDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UserProfile.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserProfile person = (UserProfile) o;

        try {
            personDetailsService.loadUserByUsername(person.getEmail());
        } catch (UsernameNotFoundException ignored) {
            return; // все ок, пользователь не найден
        }

        errors.rejectValue("email", "", "User with this email exists");
    }
}
