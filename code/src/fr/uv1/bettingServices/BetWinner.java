package fr.uv1.bettingServices;

import java.util.ArrayList;

import fr.uv1.bettingServices.exceptions.BadParametersException;

public class BetWinner extends Bet {

    private boolean winnerSettled;
	private Competitor first;



	public BetWinner(){
    	
    	
        //super()
    }
    
   
    
    
  	//winner getter
  		public Competitor getWinner(){
  			if(this.winnerSettled){
  				return this.first;
  			}
  			return null;
  		}
  		
  		
  		
  		public void settleWinner(Competitor winner) throws BadParametersException{
  			//credits the users who won the on winner bet in this competition
  			this.first = winner; // stocking the winner 
  
  			this.winnerSettled = true;
  		}
    
}
