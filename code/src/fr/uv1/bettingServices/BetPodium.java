package fr.uv1.bettingServices;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.HTMLDocument.Iterator;

import fr.uv1.bettingServices.exceptions.BadParametersException;

public class BetPodium extends Bet {
	
private boolean podiumSettled=false;
private Competitor first;
private Competitor second;
private Competitor third;



public void BetWinner(){
    	
    	
        //super()
    }
	
	//podium getter
  	public ArrayList<Competitor> getPodium(){
  		ArrayList<Competitor> winners = new ArrayList<Competitor>();
  		if(this.podiumSettled){
  			winners.add(this.first);
  			winners.add(this.second);
  			winners.add(this.third);
  		}
  		
  		return winners;
  	}
	
  		public void settlePodium(Competitor first,Competitor second,Competitor third) throws BadParametersException{
  			//credits the users who won the on winner bet in this competition
  			//stocking and updating podium
  			this.first = first ;
  			this.second = second;
  			this.third = third;
  			
  			this.podiumSettled = true;
  			
  		}
  		
  		
  		
  		
  		
  		
	
}
