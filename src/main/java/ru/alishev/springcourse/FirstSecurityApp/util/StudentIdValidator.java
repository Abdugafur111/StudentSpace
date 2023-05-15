package ru.alishev.springcourse.FirstSecurityApp.util;

import ru.alishev.springcourse.FirstSecurityApp.models.UserProfile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StudentIdValidator implements ConstraintValidator<UserProfile.StudentIdConstraint, Long> {

    @Override
    public void initialize(UserProfile.StudentIdConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(Long studentId, ConstraintValidatorContext constraintValidatorContext) {
        if (studentId == null) {
            return true; // Skip validation if studentId is null
        }

        String studentIdString = String.valueOf(studentId);
        return studentIdString.length() == 7;
    }


}
