package fr.uv1.bettingServices.exceptions;

public class SubscriberException extends Exception {
	
	public SubscriberException(){
		super();
	}

	public SubscriberException(String motif){
		super(motif);
	}
}
