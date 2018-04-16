package fr.uv1.bettingServices.exceptions;

public class ExistingSubscriberException extends Exception {
	
	public ExistingSubscriberException() {
		
		System.out.println("This subscriber already exists on the betting site.");
	}
}
