package fr.uv1.bettingServices.exceptions;

public class SubscriberException extends Exception {
	
	public SubscriberException(){
		super();
	}

	public SubscriberException(String motif){
		super(motif);
	}
	
	public String getMessage() {
		
		return "Ce joueur est toujours mineur : il a moins de 18 ans.";
	}
}
