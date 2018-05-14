package fr.uv1.bettingServices;

import java.util.ArrayList;

import fr.uv1.bettingServices.exceptions.*;
import fr.uv1.utils.MyCalendar;

public class BetWinner extends Bet {

    private boolean winnerSettled;
    private Competitor first ;


  	//Constructor
	public BetWinner(long numberTokens, Subscriber subscriber,ArrayList<Competition> competitions, Competitor winner) throws BadParametersException, CompetitionException {
		super(numberTokens,subscriber,competitions);
		this.first=winner ;
		this.betId++;
		
	} 
    
  	//winner getter
  	public ArrayList<Competitor> getWinner(){
  		ArrayList<Competitor> winnerBet = new ArrayList<Competitor>();
  		if(this.winnerSettled){
  			winnerBet.add(this.first);
  			return winnerBet;
  		}
  		return null ;
  
  		
  	}
  		
  		
  	public void settleWinner(Competitor winner) throws BadParametersException{
  		//credits the users who won the on winner bet in this competition
  		this.first=winner; // stocking the winner 
  		this.winnerSettled = true;
  	}
    
    
    
    public static void main(String[] args){
  	
  	// test de la classe BetWinner
  	
  	 
  	}
}
