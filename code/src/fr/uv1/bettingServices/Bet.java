package fr.uv1.bettingServices;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import fr.uv1.bettingServices.exceptions.BadParametersException;
import fr.uv1.bettingServices.exceptions.CompetitionException;

public abstract class Bet {

    ArrayList<Competitor> competitors;
    protected Competition competition;
    protected Subscriber subscriber ;
    protected long numberTokens;
    protected int betId;
    
    

	/*
	 *  Constructor 
	 */
	
	public Bet( long numberTokens, Subscriber subscriber,Competition competition) throws BadParametersException, CompetitionException{
		if (numberTokens < 0)
			throw new BadParametersException("numberTokens is inferior than O");
			
        else 
           this.numberTokens=numberTokens ;
		
		if(competition.competitionEnded())
			throw new CompetitionException("bet impossible cause competition is closed");
		else 
		    this.competition = competition;
		//this.id = id;
		this.subscriber = subscriber;
		
	}
	
	
	// Getter methods
    public long getNumberTokens() {
		return this.numberTokens;
	}
     
    public int getBetId() {
		return this.betId;
	}
    
    // is Subscriber existe
    public boolean isSubscriber(Subscriber subscriber) {
    	
        return this.subscriber.equals(subscriber);
    }
     
     
    public ArrayList<Competitor> getCompetitors() {
        return competitors;
    }
    
    
    public Competition getCompetition() {
        return competition;
    }


	public Subscriber getSubscriber() {
		// TODO Auto-generated method stub
		
		return this.subscriber;
	}
    
    //Setter methods

    public void setNumberTokens(long numberTokens) {
        this.numberTokens= numberTokens;
    }
    

    
    // To String
    public String toString() {
		
		String response = "This is bet ";
		return response;
	}


    
}
