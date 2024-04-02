package com.joalvarez.springcontainer.validation.interfaces;

import com.joalvarez.springcontainer.validation.ExistsByUsernameOrEmailValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ExistsByUsernameOrEmailValidation.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistsByUsernameOrEmail {

	String message() default "User or email already exists.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
