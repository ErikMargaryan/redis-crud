package com.understand.anothertestredis.validation.validator;

import com.understand.anothertestredis.service.UserService;
import com.understand.anothertestredis.validation.annotation.UsernameValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.stream.Collectors;

@Component
public class DuplicateUserValidator implements ConstraintValidator<UsernameValidation, String> {

    @Autowired
    private UserService userService;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext cxt) {

        return userService.findAll().stream().filter(userEntity1 -> userEntity1
                .getUsername().equals(username)).collect(Collectors.toList()).isEmpty();
    }

}
