package fr.uv1.bettingServices.exceptions;

public class ExistingSubscriberException extends Exception {
	
	public String getMessage() {
		
		return "Ce joueur existe déjà dans le site de paris.";
	}
}
