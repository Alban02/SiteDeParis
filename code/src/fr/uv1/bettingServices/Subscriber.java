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
	
	//private ArrayList<Bet> betsSubscriber;
	
	public Subscriber(String lastName, String firstName, String userName, String password, long tokenNumbers) {
		
		this.lastName = lastName;
		this.firstName = firstName;
		this.userName = userName;
		this.password = password;
		this.tokenNumbers = tokenNumbers;
	}
	
	public void authenticateSubscriber(String password) throws AuthenticationException {
		
		if(this.password != password) throw new AuthenticationException();
	}
	
	public void changeSubsPwd(String newPwd, String currentPwd) throws AuthenticationException, BadParametersException {
		
		if(password != currentPwd) throw new AuthenticationException();
		else {
			// On doit vérifier d'abord que le newPwd n'est pas un BadParameter
			password = newPwd;
		}
	}
	
	public boolean isDebitPossible(long tokenToDebit) { //On doit implémenter une exception
		
		if(tokenNumbers >= tokenToDebit) return true;
		else return false;
	}
	
	public void creditSubscriber(long numberTokens) throws SubscriberException, BadParametersException {
		
		if(numberTokens <= 0) throw new BadParametersException();
		//On doit aussi faire l'exception avec SubscriberException
		tokenNumbers += numberTokens ;
	}
	
	public void debitSubscriber(long numberTokens) throws SubscriberException, BadParametersException {
		
		if(numberTokens <= 0) throw new BadParametersException();
		//On doit aussi faire l'exception avec SubscriberException
		tokenNumbers -= numberTokens ;
	}
	
	public void cancelBet(Bet betDone) { //PAs encore terminé
		/*
		for(Bet bet : Bets) {
			if(bet.equals(betDone)) {
				Bets.remove();
			}
		}*/
	}

	public void removeBet (Bet bet){

	}
	
	public boolean equals(Subscriber subs) {
		
		if(this.userName.equals(subs.userName)) return true;
		else return false;
	}
	
	public String toString() {
		
		String response = "This subscriber calls " + lastName +  " " + firstName + ", his username is " + userName + "and he has " + tokenNumbers + " tokens." ;
		return response;
	}
	
	public static void main(String[] args) {
/*
		Scanner scan = new Scanner(System.in);
		Subscriber subs1 = Subscriber("Alban", "GOUGOUA", "NABLA", "Zépélélé", 100L);
		Subscriber subs2 = Subscriber("Ange", "GOUGOUA", "Willy", "Zagbayou", 100L);
		System.out.println("Alban est-il Ange ? " + subs1.equals(subs2));
		System.out.print(subs1.toString());
		System.out.print(subs2.toString());
		System.out.println("Authentifiez-vous s'il vous plaît, Willy !");
		String password = scan.nextLine();
		subs2.authenticateSubscriber(password);
		System.out.println("Authentifiez-vous s'il vous plaît, NABLA !");
		password = scan.nextLine();
		subs1.authenticateSubscriber(password);
		subs1.isDebitPossible(150L);
		*/
	}
	
}
