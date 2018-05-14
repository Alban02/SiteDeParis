package fr.uv1.bettingServices;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import fr.uv1.bettingServices.exceptions.*;


public abstract class Bet {
	
    protected ArrayList<Competition> competitions;
    protected ArrayList<Competitor> competitors ;
    protected Subscriber subscriber ;
    protected long numberTokens;
    protected static long betId=0;
    
    

	/*
	 *  Constructor 
	 */
	
	public Bet( long numberTokens, Subscriber subscriber,ArrayList<Competition> competitions) throws BadParametersException, CompetitionException{
		if (numberTokens <= 0)
			throw new BadParametersException("numberTokens is inferior than O");
        else 
           this.numberTokens=numberTokens ;
		
		for (Competition comp : competitions ){
			if(comp.competitionEnded()) {
				throw new CompetitionException("bet impossible cause competition is closed");
			}
			
			else 
			    this.competitions.add(comp) ;
		}
			
		
		this.subscriber = subscriber;
		
	}
	
	
	// Getter methods
    public long getNumberTokens() {
		return this.numberTokens;
	}
    
    public Subscriber getSubscriber() {
		return this.subscriber;
	}
    
    public ArrayList<Competitor> getCompetitors(){
    	return competitors ;
    }
    
     
    public long getBetId() {
		return this.betId;
	}
    
    // is Subscriber existe
    public boolean isSubscriber(Subscriber subscriber) {
    	
        return this.subscriber.equals(subscriber);
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
