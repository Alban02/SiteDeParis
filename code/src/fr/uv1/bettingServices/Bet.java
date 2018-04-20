package fr.uv1.bettingServices;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import fr.uv1.bettingServices.exceptions.BadParametersException;
import fr.uv1.bettingServices.exceptions.CompetitionException;

public abstract class Bet {
    ArrayList<Competitor> competitors;
    
    Competition competition;
    Subscriber subscriber ;
    
    long stake;
    int betId;
    
    

	/*
	 *  Constructor 
	 */
	
	public Bet( long stake, Subscriber subscriber,Competition competition) throws  BadParametersException, CompetitionException{
		if (stake < 0)
			throw new BadParametersException();//"stake number inferior than O"
		/*
		if(competition.competitionEnded())
			throw new CompetitionException();//"bet impossible cause competition is closed"
		*/
		this.stake = stake;
		//this.id = id;
		this.subscriber = subscriber;
		this.competition = competition;
		
	}
	
	/*
	 *  Constructor 
	 */
	public Bet() {
		
		
	}
    
	
	// Getter methods
    public long getStake() {
		return this.stake;
	}
     
    public int getBetId() {
		return this.betId;
	}
    
    // is Subscriber existe
    public boolean isSubscriber(Subscriber subscriber) {
    	
        return this.subscriber.equals(subscriber);
    }
     
    
    public Competition getCompetition() {
        return competition;
    }
     
     
    public ArrayList<Competitor> getCompetitors() {
        return competitors;
    }

    
    //Setter methods

    public void setStake(long stake) {
        this.stake= stake;
    }
    
    
    // Delete Bet
    public void deleteBet(){
    
        //this=null;
    }
    
    
    
    // To String
    public String toString() {
		
		String response = "This is bet";
		return response;
	}


	public Subscriber getSubscriber() {
		// TODO Auto-generated method stub
		
		return this.subscriber;
	}



    
}
