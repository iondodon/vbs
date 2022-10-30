package com.babcock.vbs.business.validation;

import com.babcock.vbs.business.validation.validator.PeriodValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE})
@Constraint(validatedBy = PeriodValidator.class)
public @interface ValidPeriod {
    String message() default "Invalid period";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}