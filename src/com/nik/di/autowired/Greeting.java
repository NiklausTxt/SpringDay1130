package com.nik.di.autowired;

public class Greeting {
	
	private String language;
	private String value;

	public String greeting() {
		return value + " is 'HELLO' in " + language;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
