package fr.uv1.bettingServices;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.HTMLDocument.Iterator;

import fr.uv1.bettingServices.exceptions.*;
import fr.uv1.utils.MyCalendar;

public class BetPodium extends Bet {
	
	private boolean podiumSettled=false;
	private Competitor first;
	private Competitor second;
	private Competitor third;
	ArrayList<Competition> podiumBet ;


    //Constructor
	public BetPodium(long numberTokens,Subscriber subscriber,ArrayList<Competition> competitions, Competitor first, Competitor second, Competitor third) throws BadParametersException, CompetitionException {
		super(numberTokens,subscriber,competitions);
		this.first = first;
		this.second = second;
		this.third = third;
		this.betId++;
		
	} 
	
	//podium getter
  	public ArrayList<Competitor> getPodium(){
  		ArrayList<Competitor> podiumBet = new ArrayList<Competitor>();
  		if(this.podiumSettled){
  			podiumBet.add(this.first);
  			podiumBet.add(this.second);
  			podiumBet.add(this.third);
  		}
  		
  		return podiumBet;
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
  	    
  	 
  	}
  		
	
}
