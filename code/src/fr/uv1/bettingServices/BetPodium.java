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


    //Constructor
	public BetPodium(long numberTokens,Subscriber subscriber,Competition competition, Competitor first, Competitor second, Competitor third) throws BadParametersException, CompetitionException {
		super(numberTokens,subscriber,competition);
		this.first = first;
		this.second = second;
		this.third = third;
		
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
  		
  	public static void main(String[] args){
  	
  	// test de la classe BetPodium
  	    Suscriber suscriber = new Suscriber("Alban", "GOUGOUA", "NABLA", "MonbonPetit");
  	    MyCalendar dateComp= new MyCalendar(2018, 4, 23) ;
  	    Competition comp = new Competition("LIGA", dateComp, Collection<Competitor> competitors)
  	    BetPodium podium=new BetPodium(10L,suscriber,comp) ;
  	 
  	}
  		
	
}
