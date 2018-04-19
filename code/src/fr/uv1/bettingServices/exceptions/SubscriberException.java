package fr.uv1.bettingServices.exceptions;

public class SubscriberException extends Exception {
	
	public SubscriberException() {
		
		System.out.println("Ce joueur est toujours mineur : il a moins de 18 ans.");
	}
}
