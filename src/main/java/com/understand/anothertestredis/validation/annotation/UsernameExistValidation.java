package com.understand.anothertestredis.validation.annotation;

import com.understand.anothertestredis.validation.validator.ExistUserValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;

@Target(FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ExistUserValidator.class})
public @interface UsernameExistValidation {
    //error message
    public String message() default "This username haven't been";

    //represents group of constraints
    public Class<?>[] groups() default {};

    //represents additional information about annotation
    public Class<? extends Payload>[] payload() default {};
}
