package org.springframework.samples.mvc.validation.custom;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * JSR-303 Custom Validator using the @IsPalindrome annotation
 * 
 * @author mgill
 *
 */
public class PalindromeValidator implements ConstraintValidator<IsPalindrome, String> {
	 
	@Override
	public void initialize(IsPalindrome validationDef) {
		
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value.equals(new StringBuffer(value).reverse().toString());
	}
}
