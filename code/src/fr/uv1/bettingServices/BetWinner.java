package fr.uv1.bettingServices;

import java.util.ArrayList;

import fr.uv1.bettingServices.exceptions.BadParametersException;

public class BetWinner extends Bet {

    private boolean winnerSettled;
	private Competitor first;


  	//Constructor
	public BetWinner(long numberTokens,Subscriber subscriber,Competition competition, Competitor winner) throws BadParametersException, CompetitionException {
		super(numberTokens,subscriber,competition);
		this.winner=winner;
		
	} 
    
  	//winner getter
  	public Competitor getWinner(){
  		if(this.winnerSettled){
  			return this.first;
  		}
  		
  	}
  		
  		
  	public void settleWinner(Competitor winner) throws BadParametersException{
  		//credits the users who won the on winner bet in this competition
  		this.first = winner; // stocking the winner 
  		this.winnerSettled = true;
  	}
    
    
    
    public static void main(String[] args){
  	
  	// test de la classe BetWinner
  	
  	    Suscriber suscriber = new Suscriber("Alban", "GOUGOUA", "NABLA", "MonbonPetit");
  	    MyCalendar dateComp= new MyCalendar(2018, 4, 23) ;
  	    Collection<Competitor> competitors ;
  	    //Competitor competiteur1=new Competitor()
  	    Competition comp = new Competition("LIGA", dateComp, Collection<Competitor> competitors) ;
  	    BetPodium podium=new BetPodium(10L,suscriber,comp,) ;
  	 
  	}
}
