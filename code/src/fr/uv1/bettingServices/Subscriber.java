package fr.uv1.bettingServices;

import java.util.*;

import fr.uv1.bettingServices.exceptions.*;

/**
 * 
 * @author Alban GOUGOUA & Henri-Michel KOUASSI <br>
 * <br>
 *         This class describes all the attributes and methods that one 
 *         could use on the betting software. <br>
 * 
 */
public class Subscriber {
	
	private String lastName;
	private String firstName;
	private String userName;
	private String password;
	private long tokenNumbers;
	
	private ArrayList<Bet> betsSubscriber;
	
	public Subscriber(String lastName, String firstName, String userName, String password, long tokenNumbers) {
		
		this.lastName = lastName;
		this.firstName = firstName;
		this.userName = userName;
		this.password = password;
		this.tokenNumbers = tokenNumbers;
	}
	
	public void authenticateSubscriber(String password) throws AuthenticationException {
		
		try {
			if(this.password != password) throw new AuthenticationException();
		}
		catch(AuthenticationException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void changeSubsPwd(String newPwd, String currentPwd) throws AuthenticationException { //BadParametersException à ajouter et implémenter
		
		if(password != currentPwd) {
			try {
				throw new AuthenticationException();
			}
			catch(AuthenticationException e) {
				System.out.println(e.getMessage());
			}
		}
		else {
			password = newPwd;
		}
	}
	
	public boolean isDebitPossible(long tokenToDebit) throws BadParametersException {
		
		if(tokenToDebit <= 0) {
			try {
				throw new BadParametersException();
			}
			catch(BadParametersException e) {
				System.out.println("Erreur !!!");
				return false;
			}
		}
		else {
			if(tokenNumbers >= tokenToDebit) return true;
			else return false;
		}
	}
	
	public void creditSubscriber(long numberTokens) throws BadParametersException {
		
		if(numberTokens <= 0) {
			try {
				throw new BadParametersException();
			}
			catch(BadParametersException e) {
				System.out.println("Erreur !!!");
			}
		}
		else {
			tokenNumbers += numberTokens ;
		}
	}
	
	public void debitSubscriber(long numberTokens) throws BadParametersException {
		
		if(numberTokens <= 0) {
			try {
				throw new BadParametersException();
			}
			catch(BadParametersException e) {
				System.out.println("Erreur !!!");
			}
		}
		else {
			tokenNumbers -= numberTokens ;
		}
	}
	
	public void cancelBet(Bet betDone) throws BadParametersException { //Pas encore terminé
		
		long stake = 0;
		for(Bet bet : betsSubscriber) {
			if(bet.equals(betDone)) {
				stake = betDone.stake;
				betsSubscriber.remove(betDone);
			}
		}
		this.creditSubscriber(stake);
	}

	public void removeBet(Bet bet) {

	}
	
	public boolean equals(Subscriber subs) {
		
		if(this.userName.equals(subs.userName)) return true;
		else return false;
	}
	
	public String toString() {
		
		String response = "This subscriber calls " + lastName +  " " + firstName + ", his username is " + userName + " and he has " + tokenNumbers + " tokens." ;
		return response;
	}
	
	public static void main(String[] args) throws AuthenticationException, BadParametersException {

		Scanner scan = new Scanner(System.in);
		Subscriber subs1 = new Subscriber("Alban", "GOUGOUA", "NABLA", "Zépélélé", 100L);
		Subscriber subs2 = new Subscriber("Ange", "GOUGOUA", "Willy", "Zagbayou", 100L);
		System.out.println("Alban est-il Ange ? " + subs1.equals(subs2));
		System.out.println(subs1.toString());
		System.out.println(subs2.toString());
		System.out.println(subs1.isDebitPossible(10L));
		subs1.changeSubsPwd("OK", "Zépélél");
		scan.close();
	}
	
}
