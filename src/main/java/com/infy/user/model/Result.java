package com.infy.user.model;

public class Result {
	private String message;
	public Result(String message) {
		super();
		this.message = message;
	}
	
	public Result() {
		super();
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
