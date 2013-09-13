package org.springframework.samples.mvc.validation.custom;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Custom Validator Definition 
 * 
 * @autor mgill
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PalindromeValidator.class)
@Documented
public @interface IsPalindrome {
	String message() default "{field must be a palindrome}";
	
	Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
