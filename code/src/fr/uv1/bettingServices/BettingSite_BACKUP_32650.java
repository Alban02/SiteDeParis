package fr.uv1.bettingServices;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

import fr.uv1.bettingServices.exceptions.*;

import fr.uv1.utils.MyCalendar;

public class BettingSite implements Betting {

    Manager manager;//= new Manager("password");
    Collection<Competitor> listCompetitors = new HashSet<Competitor>();
    Collection<Competition> listCompetitions = new HashSet<Competition>();
    Collection<Subscriber> listSubscriber = new HashSet<Subscriber>();

    /***********************************************************************
     * MANAGER FONCTIONNALITIES
     ***********************************************************************/

    /**
     * authenticate manager.
     *
     * @param managerPwd
     *            the manager's password.
     *
     * @throws AuthenticationException
     *             raised if the manager's password is incorrect.
     */

    public void authenticateMngr(String managerPwd) throws AuthenticationException {
       
    	this.manager.authenticateMngr(managerPwd);
    }

    /**
     * register a subscriber (person).
     *
     * @param lastName
     *            the last name of the subscriber.
     * @param firstName
     *            the first name of the subscriber.
     * @param username
     *            the username of the subscriber.
     * @param borndate
     *            the borndate of the subscriber.
     * @param managerPwd
     *            the manager's password.
     *
     * @throws AuthenticationException
     *             raised if the manager's password is incorrect.
     * @throws ExistingSubscriberException
     *             raised if a subscriber exists with the same username.
     * @throws SubscriberException
     *             raised if subscriber is minor.
     * @throws BadParametersException
     *             raised if last name, first name, username or borndate are
     *             invalid or not instantiated.
     *
     * @return password for the new subscriber.
     */

    public String subscribe(String lastName, String firstName, String username, String borndate, String managerPwd) throws AuthenticationException, ExistingSubscriberException, SubscriberException, BadParametersException {
    	
    	this.authenticateMngr(managerPwd);
    	
    	Subscriber newSubscriber = this.findSubscriberByUserName(username);
    	if(newSubscriber == null) {
    		//Age
    		DateFormat formatDeDate = new SimpleDateFormat("dd/MM/yyyy");
    		try {
				if(formatDeDate.parse(borndate) == null) throw new BadParametersException();
			} catch(ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

    		Calendar birthday = Calendar.getInstance();
    		try {
				birthday.setTime(formatDeDate.parse(borndate));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		Calendar today = Calendar.getInstance();
			int age = today.get(Calendar.YEAR) - birthday.get(Calendar.YEAR);
    		if(age < 18) throw new SubscriberException("Ce joueur est toujours mineur : il a moins de 18 ans.");
    		//check if last name, first name, username or borndate are invalid or not instantiated.
    		
    		//generate Password
    		 String passwordChars = "0123456789abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ";
    		 String password = "";
    		 Random randomGenerator = new Random();
    	     for (int i=0; i<15; i++) {
    	    	  int index = randomGenerator.nextInt(passwordChars.length());
    	    	  password += passwordChars.substring(index, index+1);
    	     }
    	      
    	     //Add the new subscriber
    	     newSubscriber = new Subscriber(lastName, firstName, username, password);
    	     this.listSubscriber.add(newSubscriber);
    	     return password;
    		
    	}
    	
    	else throw new ExistingSubscriberException("Ce joueur existe déjà !");
    }

    /**
     * delete a subscriber. His currents bets are canceled. He looses betted
     * tokens.
     *
     * @param username
     *            the username of the subscriber.
     * @param managerPwd
     *            the manager's password.
     *
     * @throws AuthenticationException
     *             raised if the manager's password is incorrect.
     * @throws ExistingSubscriberException
     *             raised if username is not registered.
     *
     * @return number of tokens remaining in the subscriber's account
     */
    public long unsubscribe(String username, String managerPwd) throws AuthenticationException, ExistingSubscriberException {
    	
    	this.authenticateMngr(managerPwd);
    	
    	Subscriber unSubscriber = this.findSubscriberByUserName(username);
    	if(unSubscriber != null) {
			
    		/*// Récupération des id des différents paris du joueur.
    		ArrayList<Bet> betsSubscriber = unSubscriber.getBetsSubscriber();
    		ArrayList<Integer> betIds = new ArrayList<Integer>();
    		for(Bet bet : betsSubscriber) {
    			betIds.add(bet.betId);
    		}
    		*/
    		// Annulation des différents paris.
    		unSubscriber.cancelAllBets();
    		/* Une méthode au niveau de Competition permettant de supprimer un pari doit être implémenter pour pouvoir
    		 * le supprimer à notre niveau dans unsubscribe
    		for(int id : betIds) {
    			
    		}
    		*/
    		
    		// can't retrieve token;
    		this.listSubscriber.remove(unSubscriber);
    		//some work here
    		return unSubscriber.getNumberTokens();
    		
    	}
        
    	else throw new ExistingSubscriberException("Ce joueur n'est pas enregistré.");
    }
    
    /**
     * list subscribers.
     *
     * @param managerPwd
     *            the manager's password.
     *
     * @throws AuthenticationException
     *             raised if the manager's password is incorrect.
     *
     * @return a list of list of strings:
     *         <ul>
     *         <li>subscriber's lastname</li>
     *         <li>subscriber's firstname</li>
     *         <li>subscriber's username</li>
     *         </ul>
     */
    public List<List<String>> listSubscribers(String managerPwd) throws AuthenticationException {
    	
    	this.authenticateMngr(managerPwd);
    	
    	List<List<String>> listSubscribers = new ArrayList<List<String>>();
    	List<String> dataSubscriber = new LinkedList<String>();
    	for(Subscriber subs : listSubscriber) {
    		dataSubscriber.add(subs.getLastName());
    		dataSubscriber.add(subs.getFirstName());
    		dataSubscriber.add(subs.getUsername());
    		listSubscribers.add(dataSubscriber);
    		dataSubscriber = new LinkedList<String>();
    	}
    	
        return listSubscribers;
    }
    
    /**
     * add a competition.
     *
     * @param competition
     *            the name of the competition.
     * @param closingDate
     *            last date to bet.
     * @param competitors
     *            the collection of competitors for the competition.
     * @param managerPwd
     *            the manager's password.
     *
     * @throws AuthenticationException
     *             raised if the the manager's password is incorrect.
     * @throws ExistingCompetitionException
     *             raised if a competition with the same name already exists.
     * @throws CompetitionException
     *             raised if closing date is in the past (competition closed);
     *             there are less than two competitors; two or more competitors
     *             are the same (firstname, lastname, borndate).
     * @throws BadParametersException
     *             raised if name of competition is invalid; list of competitors
     *             not instantiated; (firstname, lastname, borndate or name if a
     *             team competitor) of one or more of the competitors is
     *             invalid.
     */

    public void addCompetition(String competition, Calendar closingDate, Collection<Competitor> competitors, String managerPwd) throws AuthenticationException, ExistingCompetitionException, CompetitionException, BadParametersException {
    	authenticateMngr(managerPwd);
    	isCompetitionPossible(competition, closingDate, competitors);
        listCompetitions.add(new Competition(competition, closingDate, competitors));
    }
    /**
     * cancel a competition.
     *
     * @param competition
     *            the name of the competition.
     * @param managerPwd
     *            the manager's password.
     *
     * @throws AuthenticationException
     *             raised if the the manager's password is incorrect.
     * @throws ExistingCompetitionException
     *             raised if the competition does not exist.
     * @throws CompetitionException
     *             raised if the closing date is in the past (competition
     *             closed).
     */

    public void cancelCompetition(String competition, String managerPwd) throws AuthenticationException, ExistingCompetitionException, CompetitionException {
    	authenticateMngr(managerPwd);
    	Competition comp = findCompetitionByName(competition);
    	comp.cancelAllBets();
    	
    	
    }
    /**
     * delete a competition.
     *
     * @param competition
     *            the name of the competition.
     * @param managerPwd
     *            the manager's password.
     *
     * @throws AuthenticationException
     *             raised if the the manager's password is incorrect.
     * @throws ExistingCompetitionException
     *             raised if the competition does not exist.
     * @throws CompetitionException
     *             raised if the closing date is in the futur (competition
     *             opened).
     */

    public void deleteCompetition(String competition, String managerPwd) throws AuthenticationException, ExistingCompetitionException, CompetitionException {
    	authenticateMngr(managerPwd);
    	Competition comp =findCompetitionByName (competition);
    	comp.cancelAllBets();
    	listCompetitions.remove(comp);
    	
    }
    /**
     * add a competitor to a competition.
     *
     * @param competition
     *            the name of the competition.
     * @param competitor
     *            infos about the competitor.
     * @param managerPwd
     *            the manager's password.
     *
     * @throws AuthenticationException
     *             raised if the the manager's password is incorrect.
     * @throws ExistingCompetitionException
     *             raised if the competition does not exist.
     * @throws CompetitionException
     *             raised if the closing date of the competition is in the past
     *             (competition closed).
     * @throws ExistingCompetitorException
     *             raised if the competitor is already registered for the
     *             competition.
     * @throws BadParametersException
     *             raised if the (firstname, lastname, borndate or name if team
     *             competitors) of the competitor is invalid.
     */

    public void addCompetitor(String competition, Competitor competitor, String managerPwd) throws AuthenticationException, ExistingCompetitionException, CompetitionException, ExistingCompetitorException, BadParametersException {
    	authenticateMngr(managerPwd);
    	Competition comp =findCompetitionByName (competition);
    	comp.addCompetitor(competitor);
    }
    /**
     * create a competitor (person) instance. If the competitor is already
     * registered, the existing instance is returned. The instance is not
     * persisted.
     *
     * @param lastName
     *            the last name of the competitor.
     * @param firstName
     *            the first name of the competitor.
     * @param borndate
     *            the borndate of the competitor.
     * @param managerPwd
     *            the manager's password.
     *
     * @throws AuthenticationException
     *             raised if the manager's password is incorrect.
     * @throws BadParametersException
     *             raised if last name, first name or borndate are invalid.
     *
     * @return Competitor instance.
     */

    public Competitor createCompetitor(String lastName, String firstName, String borndate, String managerPwd) throws AuthenticationException, BadParametersException {
        this.authenticateMngr(managerPwd);
        Competitor individual ;
        individual = this.findCompetitorByName(lastName,firstName);
        if (individual == null) {
            individual = new Individual(lastName,firstName,borndate);
            if (!(individual.hasValidName())) throw new BadParametersException();
            this.listCompetitors.add(individual);
        }

        return individual;
    }

    /**
     * create competitor (team) instance. If the competitor is already
     * registered, the existing instance is returned. The instance is not
     * persisted.
     *
     * @param name
     *            the name of the team.
     * @param managerPwd
     *            the manager's password.
     *
     * @throws AuthenticationException
     *             raised if the manager's password is incorrect.
     * @throws BadParametersException
     *             raised if name is invalid.
     *
     * @return Competitor instance.
     */

    public Competitor createCompetitor(String name, String managerPwd) throws AuthenticationException, BadParametersException {
        this.authenticateMngr(managerPwd);
        Competitor team ;
        team = this.findCompetitorByName(name);
        if (team == null) {
            team = new Team(name);
            if (!(team.hasValidName())) throw new BadParametersException();
            this.listCompetitors.add(team);
        }


        return team;
    }
    /**
     * delete a competitor for a competition.
     *
     * @param competition
     *            the name of the competition.
     * @param competitor
     *            infos about the competitor.
     * @param managerPwd
     *            the manager's password.
     *
     * @throws AuthenticationException
     *             raised if the the manager's password is incorrect.
     * @throws ExistingCompetitionException
     *             raised if the competition does not exist.
     * @throws CompetitionException
     *             raised if the closing date of the competition is in the past
     *             (competition closed) ; the number of remaining competitors is
     *             2 before deleting.
     * @throws ExistingCompetitorException
     *             raised if the competitor is not registered for the
     *             competition.
     */

    public void deleteCompetitor(String competition, Competitor competitor, String managerPwd) throws AuthenticationException, ExistingCompetitionException, CompetitionException, ExistingCompetitorException {
        this.authenticateMngr(managerPwd);
        Competition competitionInstance = findCompetitionByName(competition);
        if (competitionInstance == null) throw new ExistingCompetitionException();
        competitionInstance.deleteCompetitor(competitor);
        this.isToDeleteCompetitor(competitor);

    }

    private void isToDeleteCompetitor(Competitor competitor) {
        if (competitor.getCompetitions().size()==0) this.listCompetitors.remove(competitor);
        for (Competitor c : competitor.getMembers()) {
            if (c.getCompetitions().size()==0) this.listCompetitors.remove(c);
        }
    }

    private Competitor findCompetitorByName (String name) {
        for (Competitor team : listCompetitors){
            if (team.sameName(name)) return team;
        }
        return null ;
    }

    private Competitor findCompetitorByName (String lastName, String firstName) {
        for (Competitor individual : listCompetitors){
            if (individual.sameName(lastName,firstName)) return individual;
        }
        return null ;
    }
    
    /**
     * credit number of tokens of a subscriber.
     *
     * @param username
     *            subscriber's username.
     * @param numberTokens
     *            number of tokens to credit.
     * @param managerPwd
     *            the manager's password.
     *
     *
     * @throws AuthenticationException
     *             raised if the the manager's password is incorrect.
     * @throws ExistingSubscriberException
     *             raised if the subscriber (username) is not registered.
     * @throws BadParametersException
     *             raised if number of tokens is less than (or equals to) 0.
     */
    public void creditSubscriber(String username, long numberTokens, String managerPwd) throws AuthenticationException, ExistingSubscriberException, BadParametersException {

    	this.authenticateMngr(managerPwd);
    	
    	Subscriber subscriberToCredit = this.findSubscriberByUserName(username);
    	if(subscriberToCredit != null) {
    		subscriberToCredit.creditSubscriber(numberTokens);
    	}
    }
    
    /**
     * debit a subscriber account with a number of tokens.
     *
     * @param username
     *            subscriber's username.
     * @param numberTokens
     *            number of tokens to debit.
     * @param managerPwd
     *            the manager's password.
     *
     * @throws AuthenticationException
     *             raised if the the manager's password is incorrect.
     * @throws ExistingSubscriberException
     *             raised if the subscriber (username) is not registered.
     * @throws SubscriberException
     *             raised if number of tokens not enough.
     * @throws BadParametersException
     *             raised if number of tokens is less than (or equals to) 0.
     *
     */
    public void debitSubscriber(String username, long numberTokens, String managerPwd) throws AuthenticationException, ExistingSubscriberException, SubscriberException, BadParametersException {

    	this.authenticateMngr(managerPwd);
    	
    	Subscriber subscriberToDebit = this.findSubscriberByUserName(username);
    	if(subscriberToDebit != null) {
    		subscriberToDebit.debitSubscriber(numberTokens);	
    	}
    }
    
    /**
     * settle bets on winner. <br>
     * Each subscriber betting on this competition with winner a_winner is
     * credited with a number of tokens equals to: <br>
     * (number of tokens betted * total tokens betted for the competition) /
     * total number of tokens betted for the winner <br>
     * If no subscriber bets on the right competitor (the real winner), the
     * tokens betted are credited to subscribers betting on the competition
     * according to the number of tokens they betted. The competition is then
     * deleted if no more bets exist for the competition.<br>
     *
     * @param competition
     *            the name of the competition.
     * @param winner
     *            competitor winner.
     * @param managerPwd
     *            the manager's password.
     *
     * @throws AuthenticationException
     *             raised if the the manager's password is incorrect.
     * @throws ExistingCompetitionException
     *             raised if the competition does not exist.
     * @throws CompetitionException
     *             raised if there is no competitor a_winner for the
     *             competition; competition still opened.
     */

    public void settleWinner(String competition, Competitor winner, String managerPwd) throws AuthenticationException, ExistingCompetitionException, CompetitionException {
    	authenticateMngr(managerPwd);
    	Competition comp = findCompetitionByName(competition);
    	comp.settleWinner(winner);
    }
    /**
     * settle bets on podium. <br>
     * Each subscriber betting on this competition with the right podium is
     * credited with a number of tokens equals to: <br>
     * (number of tokens betted * total tokens betted for the competition) /
     * total number of tokens betted for the podium <br>
     * If no subscriber bets on the right podium, the tokens betted are credited
     * to subscribers betting on the competition according to the number of
     * tokens they betted. The competition is then deleted if no more bets exist
     * for the competition.<br>
     *
     * @param competition
     *            the name of the competition.
     * @param winner
     *            the winner.
     * @param second
     *            the second.
     * @param third
     *            the third.
     * @param managerPwd
     *            the manager's password.
     *
     * @throws AuthenticationException
     *             raised if the the manager's password is incorrect.
     * @throws ExistingCompetitionException
     *             raised if the competition does not exist.
     * @throws CompetitionException
     *             raised if two competitors in the podium are the same; no
     *             competitor (firstname, lastname, borndate or name for teams)
     *             a_winner, a_second or a_third for the competition;
     *             competition still opened
     */

    public void settlePodium(String competition, Competitor winner, Competitor second, Competitor third, String managerPwd) throws AuthenticationException, ExistingCompetitionException, CompetitionException {
    	authenticateMngr(managerPwd);
    	Competition comp = findCompetitionByName(competition);
    	comp.settlePodium(winner, second, third);
    }
    /***********************************************************************
     * SUBSCRIBERS FONCTIONNALITIES
     ***********************************************************************/

    /**
     * bet a winner for a competition <br>
     * The number of tokens of the subscriber is debited.
     *
     * @param numberTokens
     *            number of tokens to bet.
     * @param competition
     *            name of the competition.
     * @param winner
     *            competitor to bet (winner).
     * @param username
     *            subscriber's username.
     * @param pwdSubs
     *            subscriber's password.
     *
     * @throws AuthenticationException
     *             raised if (username, password) does not exist.
     * @throws ExistingCompetitionException
     *             raised if the competition does not exist.
     * @throws CompetitionException
     *             raised if there is no competitor a_winner for the
     *             competition; competition is closed (closing date is in the
     *             past); the player is a competitor of the competition.
     * @throws SubscriberException
     *             raised if subscriber has not enough tokens.
     * @throws BadParametersException
     *             raised if number of tokens less than 0.
     *
     */
    public void betOnWinner(long numberTokens, String competition, Competitor winner, String username, String pwdSubs) throws AuthenticationException, CompetitionException, ExistingCompetitionException, SubscriberException, BadParametersException {
    	
    	Subscriber subs = findSubscriberByUserName(username);
    	if(subs != null) {
    		subs.authenticateSubscriber(pwdSubs);
    		
    		ArrayList<Competition> competitions = new ArrayList<Competition>();
    		Competition comp = findCompetitionByName(competition);
    		if(comp != null) {
    			competitions.add(comp);
    			
    			subs.debitSubscriber(numberTokens);
    			
        		Bet betOnWinner = new BetWinner(numberTokens, subs, competitions, winner);
        		subs.addBet(betOnWinner);
    		}
    	}
    	
    	else throw new AuthenticationException("Ce joueur n'existe pas.");
    }
    
    /**
     * bet on podium <br>
     * The number of tokens of the subscriber is debited.
     *
     * @param username
     *            subscriber's username.
     * @param pwdSubs
     *            subscriber's password.
     * @param numberTokens
     *            number of tokens to bet.
     * @param competition
     *            the name of the competition.
     * @param winner
     *            winner to bet.
     * @param second
     *            second place.
     * @param third
     *            third place.
     *
     * @throws AuthenticationException
     *             raised if (username, password) does not exist.
     * @throws ExistingCompetitionException
     *             raised if the competition does not exist.
     * @throws CompetitionException
     *             raised if there is no competitor with name a_winner, a_second
     *             or a_third for the competition; competition is closed
     *             (closing date is in the past); the player is a competitor of
     *             the competition.
     * @throws SubscriberException
     *             raised if subscriber has not enough tokens.
     * @throws BadParametersException
     *             raised if number of tokens less than 0.
     */
    public void betOnPodium(long numberTokens, String competition, Competitor winner, Competitor second, Competitor third, String username, String pwdSubs) throws AuthenticationException, CompetitionException, ExistingCompetitionException, SubscriberException, BadParametersException {
    	
    	Subscriber subs = findSubscriberByUserName(username);
    	if(subs != null) {
    		subs.authenticateSubscriber(pwdSubs);
    		
    		ArrayList<Competition> competitions = new ArrayList<Competition>();
    		Competition comp = findCompetitionByName(competition);
    		if(comp != null) {
    			competitions.add(comp);
    			
    			subs.debitSubscriber(numberTokens);
    			
        		Bet betOnPodium = new BetPodium(numberTokens, subs, competitions, winner, second, third);
        		subs.addBet(betOnPodium);
    		}
    	}
    	
    	else throw new AuthenticationException("Ce joueur n'existe pas.");
    }
    
    /**
     * change subscriber's password.
     *
     * @param username
     *            username of the subscriber.
     * @param newPwd
     *            the new subscriber password.
     * @param currentPwd
     *            the manager's password.
     *
     * @throws AuthenticationException
     *             raised if (username, password) does not exist.
     *
     * @throws BadParametersException
     *             raised if the new password is invalid.
     */
    public void changeSubsPwd(String username, String newPwd, String currentPwd) throws AuthenticationException, BadParametersException {
    	
    	Subscriber subs = findSubscriberByUserName(username);
    	if(subs != null) {
    		subs.changeSubsPwd(newPwd, currentPwd);
    	}
    	
    	else throw new AuthenticationException("Ce joueur n'existe pas.");
    }
    
    /**
     * consult informations about a subscriber
     *
     * @param username
     *            subscriber's username.
     * @param pwdSubs
     *            subscriber's password.
     *
     * @throws AuthenticationException
     *             raised if (username, password) does not exist.
     *
     * @return list of String with:
     *         <ul>
     *         <li>subscriber's lastname</li>
     *         <li>subscriber's firstname</li>
     *         <li>subscriber's username</li>
     *         <li>number of tokens</li>
     *         <li>tokens betted</li>
     *         <li>list of current bets</li>
     *         </ul>
     * <br>
     *         All the current bets of the subscriber.
     */
    public ArrayList<String> infosSubscriber(String username, String pwdSubs) throws AuthenticationException {
        
    	Subscriber subs = findSubscriberByUserName(username);
    	if(subs != null) {
    		ArrayList<String> infosSubs = new ArrayList<String>();
    		
    		infosSubs.add(subs.getLastName());
    		infosSubs.add(subs.getFirstName());
    		infosSubs.add(subs.getUsername());
    		infosSubs.add(Long.toString(subs.getNumberTokens()));
    		
    		long stake = 0L;
    		ArrayList<Bet> betsSubscriber = subs.getBetsSubscriber();
    		for(Bet bet : betsSubscriber) {
    			stake += bet.numberTokens;
    		}
    		infosSubs.add(Long.toString(stake));
    		
    		for(Bet bet : betsSubscriber) {
    			infosSubs.add("Pari " + bet.betId + " effectué sur ");
    		}
    		
    		return infosSubs;
    	}
    	
    	else throw new AuthenticationException("Ce joueur n'existe pas."); 
    }
    /**
     * delete all bets made by a subscriber on a competition.<br>
     * subscriber's account is credited with a number of tokens corresponding to
     * the bets made by the subscriber for the competition.
     *
     * @param competition
     *            competition's name.
     * @param username
     *            subscriber's username.
     * @param pwdSubs
     *            subscriber's password.
     *
     * @throws AuthenticationException
     *             raised if (username, password) does not exist.
     * @throws CompetitionException
     *             raised if closed competition (closing date is in the past).
     * @throws ExistingCompetitionException
     *             raised if there is no competition a_competition.
     */
    public void deleteBetsCompetition(String competition, String username, String pwdSubs) throws AuthenticationException, CompetitionException, ExistingCompetitionException {
    	
    	Subscriber subs = findSubscriberByUserName(username);
    	if(subs != null) {
    		subs.authenticateSubscriber(pwdSubs);
    		
    		Competition comp = findCompetitionByName(competition);
    		if(comp != null) {
    			// On doit vérifier si la compétition a débuté ou non.
    			// Sans vérification de la condition impossible de continuer.
    		}
    	}
    }
    
    /***********************************************************************
     * VISITORS FONCTIONNALITIES
     ***********************************************************************/
    /**
     * list competitions.
     *
     * @return a collection of competitions represent a competition data:
     *         <ul>
     *         <li>competition's name</li>
     *         <li>competition's closing date</li>
     *         <li>competition's current bets</li>
     *         <li>competition's competitors</li>
     *         </ul>
     */

    public List<List<String>> listCompetitions() {
        List<List<String>> output = new LinkedList<List<String>>();
        for (Competition c : listCompetitions)
        	output.add(c.information());
        return output;
    }
    /**
     * list competitors.
     *
     * @param competition
     *            competition's name.
     *
     * @throws ExistingCompetitionException
     *             raised if the competition does not exist.
     * @throws CompetitionException
     *             raised if competition closed.
     * @return a collection of competitors for a competition. For each person
     *         competitor
     *         <ul>
     *         <li>competitor's firstname</li>
     *         <li>competitor's lastname</li>
     *         <li>competitor's borndate</li>
     *         </ul>
     *         For each team competitor <li>competitor's name</li> </ul>
     */

    public Collection<Competitor> listCompetitors(String competition) throws ExistingCompetitionException, CompetitionException {
        return null;
    }
    /**
     * consult bets on a competition.
     *
     * @param competition
     *            competition's name.
     *
     * @throws ExistingCompetitionException
     *             raised if it does not exist a competition of the name
     *             a_competition.
     *
     * @return a list of String containing the bets for the competition.
     */


    public ArrayList<String> consultBetsCompetition(String competition) throws ExistingCompetitionException {
        return null;
    }
    /**
     * consult results of a closed competition.
     *
     * @param competition
     *            competition's name.
     *
     * @throws ExistingCompetitionException
     *             raised if it does not exist a competition of the name
     *             a_competition.
     *
     * @throws CompetitionException
     *             raised if competition still opened.
     *
     * @return the list of competitors that won the competition.
     */

    public ArrayList<Competitor> consultResultsCompetition(String competition) throws ExistingCompetitionException {
        return null;
    }
    /***********************************************************************
     * ADDED METHODS
     ***********************************************************************/
    
    public void authenticateSubscriber(String username, String  password) throws AuthenticationException, BadParametersException{
    	Subscriber newSubscriber ; 
    	newSubscriber=this.findSubscriberByUserName(username);
    	if (newSubscriber!=null){
    		
    		newSubscriber.authenticateSubscriber(password);
    	}
    	
    	
<<<<<<< HEAD
    };
    public Subscriber findSubscriberByUserName (String userName) {
    	Subscriber subToReturn=null;
=======
    }
    
    private Subscriber findSubscriberByUserName (String userName) {
>>>>>>> 4d39b43a2817978bc9746ceebc1256d0ab177f3c
        for (Subscriber subs : listSubscriber){
        	
            if (subs.equals(userName))  {
            	subToReturn=subs;
            	}
            
        }
        return subToReturn;
    }


    private Competition findCompetitionByName (String name) throws ExistingCompetitionException {
    	for(Competition currcompetition : listCompetitions){
    		if (currcompetition.getName() == name){
    			return currcompetition;
    		}
    	}
    	throw new ExistingCompetitionException();
    }
    private boolean existingCompetition(String name){
    	for (Competition currcompetition : listCompetitions){
    		if (currcompetition.getName() == name){
    			return true;
    		}
    	}
    	return false;
    }
    
    private void isCompetitionPossible(String competition, Calendar closingDate, Collection<Competitor> competitors) throws ExistingCompetitionException, CompetitionException {
    	if (existingCompetition(competition))
    		throw new ExistingCompetitionException();
    	MyCalendar c = (MyCalendar) closingDate;
    	if (c.isInThePast())
    		throw new CompetitionException();
    	if (competitors.size() < 2)
    		throw new CompetitionException();
    	Iterator<Competitor> i1 = competitors.iterator();
    	Iterator<Competitor> i2 = null;
    	while (i1.hasNext())
    	{
    		Competitor c1 = i1.next();
    		Competitor c2 = null;
    		i2 = competitors.iterator();
    		while (i2.hasNext() && c2 != c1)
    			c2 = i2.next();
    		while (i2.hasNext()) {
    			c2 = i2.next();
    			if (c1.same(c2))
    				throw new CompetitionException();
    		}
    	}
    }
    //this method isjust for testing and will be deleted once the tests are over
    public String getManagerPassword () {
        return "password";
    }
    
	public static void main(String[] args) throws AuthenticationException, ExistingSubscriberException, BadParametersException, SubscriberException {
		BettingSite test= new BettingSite();
		test.manager = new Manager("password");
		String a=test.subscribe("lastName", "firstName", "username", "01/01/2000", "password");
		System.out.println(a);
		long b =test.unsubscribe("username", "password");
		System.out.println(b);
	}

	

}