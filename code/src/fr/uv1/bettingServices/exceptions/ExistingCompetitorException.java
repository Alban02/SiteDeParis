package fr.uv1.bettingServices.exceptions;

public class ExistingCompetitorException extends Exception {

    public ExistingCompetitorException() {
		super();
	}

	public ExistingCompetitorException(String motif) {
		super(motif);	
	}
}
