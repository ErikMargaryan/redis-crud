package com.understand.anothertestredis.validation.annotation;

import com.understand.anothertestredis.validation.validator.DuplicateUserValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.FIELD;

@Target(FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {DuplicateUserValidator.class})
public @interface UsernameValidation {
    //error message
    public String message() default "This username have already been";


    //represents group of constraints
    public Class<?>[] groups() default {};

    //represents additional information about annotation
    public Class<? extends Payload>[] payload() default {};
}
