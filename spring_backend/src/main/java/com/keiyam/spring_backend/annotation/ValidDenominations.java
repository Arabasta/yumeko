package com.keiyam.spring_backend.annotation;

import com.keiyam.spring_backend.validator.DenominationValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// ref for custom annotations: https://www.baeldung.com/spring-mvc-custom-validator
/**
 * Custom annotation for validating coin denominations.
 * Ensures that the list of denominations contains only valid values.
 *
 * @see DenominationValidator
 */
@Constraint(validatedBy = DenominationValidator.class)
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDenominations {

    String message() default "Invalid coin denominations";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}