package fr.uv1.bettingServices.exceptions;

public class ExistingBetException extends Exception {
	
	public String getMessage() {
		
		return "Ce pari n'existe pas.";
	}
}
