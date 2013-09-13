package org.springframework.samples.mvc.validation.custom;

import javax.validation.constraints.NotNull;

public class JavaBean {
	
	@NotNull
	@IsPalindrome
	private String input;

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

}
