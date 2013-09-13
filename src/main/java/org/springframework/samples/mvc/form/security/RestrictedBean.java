package org.springframework.samples.mvc.form.security;

public class RestrictedBean {
	
	private boolean isAdmin;

	private String hash;

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}
}
