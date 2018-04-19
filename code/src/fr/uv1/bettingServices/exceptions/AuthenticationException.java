package fr.uv1.bettingServices.exceptions;

public class AuthenticationException extends Exception {
	
	public String getMessage() {
		
		return "Le mot de passe entré est incorrect.";
	}
}
