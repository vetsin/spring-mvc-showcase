package org.springframework.samples.mvc.form.security;

import javax.validation.constraints.NotNull;


public class AssignmentBean {

	@NotNull
	private String name;
	
	private RestrictedBean privateBean;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public RestrictedBean getPrivateBean() {
		return privateBean;
	}

	public void setPrivateBean(RestrictedBean privateBean) {
		this.privateBean = privateBean;
	}
	
}
