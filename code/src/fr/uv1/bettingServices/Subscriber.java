package fr.uv1.bettingServices;

import java.util.*;
import java.util.regex.*;

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
	
	public void changeSubsPwd(String newPwd, String currentPwd) throws AuthenticationException, BadParametersException {
		
		boolean check1 = Pattern.matches("[a-zA-Z0-9]{8,}+", newPwd);
		boolean check2 = Pattern.matches("[a-zA-Z0-9]{8,}+", currentPwd);
		
		if((check1 == false) || (check2 == false)) {
			try {
				throw new BadParametersException();
			}
			catch(BadParametersException e) {
				System.out.println(e.pwdIncrorect());
			}
		}
		else {
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
	}
	
	public boolean isDebitPossible(long tokenToDebit) throws BadParametersException {
		
		if(tokenToDebit <= 0) {
			try {
				throw new BadParametersException();
			}
			catch(BadParametersException e) {
				System.out.println(e.tokensNumberIncorrect());
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
				System.out.println(e.tokensNumberIncorrect());
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
				System.out.println(e.tokensNumberIncorrect());
			}
		}
		else {
			tokenNumbers -= numberTokens ;
		}
	}
	
	public void cancelBet(Bet betDone) throws BadParametersException { //Pas encore terminé
		
		long numberTokens = 0;
		
		for(Bet bet : betsSubscriber) {
			if(bet.equals(betDone)) {
				numberTokens = betDone.stake;
				betsSubscriber.remove(betDone);
			}
		}
		this.creditSubscriber(numberTokens);
	}
	
	public void cancelAllBets() throws BadParametersException { //Pas encore terminé
		
		betsSubscriber = new ArrayList<Bet>() ;
	}
	
	public void removeBet(Bet betToDelete) throws BadParametersException { //Pas encore terminé
		
		for(Bet bet : betsSubscriber) {
			if(bet.equals(betToDelete)) {
				betsSubscriber.remove(betToDelete);
			}
		}
	}
	
	public boolean equals(Subscriber subs) {
		
		if(this.userName.equals(subs.userName)) return true;
		else return false;
	}
	
	public String toString() {
		
		String response = "Ce joueur s'appelle " + lastName +  " " + firstName + ", son nom d'utilisateur est "
				+ userName + " et il a " + tokenNumbers + " jetons." ;
		return response;
	}
	
	public static void main(String[] args) throws AuthenticationException, BadParametersException {

		Scanner scan = new Scanner(System.in);
		Subscriber subs1 = new Subscriber("Alban", "GOUGOUA", "NABLA", "MonbonPetit", 100L);
		Subscriber subs2 = new Subscriber("Ange", "GOUGOUA", "Willy", "Zagbayou", 100L);
		System.out.println("Alban est-il Ange ? " + subs1.equals(subs2));
		System.out.println(subs1.toString());
		System.out.println(subs2.toString());
		System.out.println(subs1.isDebitPossible(10L));
		subs1.changeSubsPwd("albanDonald09", "MonbonPetit");
		scan.close();
	}
	
}
