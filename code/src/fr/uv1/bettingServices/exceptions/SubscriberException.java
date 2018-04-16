package fr.uv1.bettingServices.exceptions;

public class SubscriberException extends Exception {
	
	public SubscriberException() {
		
		System.out.println("This subscriber is always minor : he is less than 18.");
	}
}
