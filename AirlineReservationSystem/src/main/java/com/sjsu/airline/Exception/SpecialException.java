package com.sjsu.airline.Exception;

import org.springframework.stereotype.Component;

@Component
public class SpecialException extends Exception{

	public int code;
	public String message;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
