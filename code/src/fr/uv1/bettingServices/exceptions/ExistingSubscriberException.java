package fr.uv1.bettingServices.exceptions;

public class ExistingSubscriberException extends Exception {
	
	public ExistingSubscriberException() {
        super();
    }
    
	public ExistingSubscriberException(String motif) {
        super(motif);
    } 
	
	public String getMessage() {
		
		return "Ce joueur existe déjà dans le site de paris.";
	}
}
