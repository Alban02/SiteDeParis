package fr.uv1.bettingServices.exceptions;

public class AuthenticationException extends Exception {
	
	public String getMessage() {
		
		return "The password is incorrect.";
	}
}
