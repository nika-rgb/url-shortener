package com.portfolio.url_shortener.short_url.api;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.hibernate.validator.internal.constraintvalidators.hv.URLValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = URLValidator.class)
@Target(value = {ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidURL {
    String message() default "URL not valid, Please ensure to fix it";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}