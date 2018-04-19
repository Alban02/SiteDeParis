package fr.uv1.bettingServices.exceptions;

public class ExistingSubscriberException extends Exception {
	
	public ExistingSubscriberException() {
		
		System.out.println("Ce joueur existe déjà dans le site de paris.");
	}
}
