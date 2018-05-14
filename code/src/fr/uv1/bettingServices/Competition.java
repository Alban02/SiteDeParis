package fr.uv1.bettingServices;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import fr.uv1.bettingServices.exceptions.BadParametersException;
import fr.uv1.bettingServices.exceptions.CompetitionException;
import fr.uv1.bettingServices.exceptions.ExistingBetException;
import fr.uv1.bettingServices.exceptions.ExistingCompetitorException;
import fr.uv1.utils.MyCalendar;

public class Competition {

    private HashSet<Competitor> competitorsList;
    private HashSet<Bet> betsList;
    private String name;
    private MyCalendar closingDate;
    private ArrayList<Competitor> winners;

    public Competition(String name, Calendar closingDate) {
        this.name = name;
        MyCalendar c = (MyCalendar) closingDate;
        this.closingDate = c;
        competitorsList = new HashSet<Competitor>();
        betsList = new HashSet<Bet>();
        winners = new ArrayList<Competitor>();
    }

    public Competition(String name, Calendar closingDate, Collection<Competitor> competitors) {
        this.name = name;
        MyCalendar c = (MyCalendar) closingDate;
        this.closingDate = c;
        competitorsList = new HashSet<Competitor>(competitors);
        betsList = new HashSet<Bet>();
        winners = new ArrayList<Competitor>();
    }
    public String getName(){
    	return this.name ;
    }
    public boolean competitionEnded (){
        return closingDate.isInThePast();
    }

    private boolean competitorExist (Competitor competitor) {
        return competitorsList.contains(competitor);
    }

    @Override
    public String toString() {
    	return null;
    }
    
    public List<String> information(){
    	LinkedList<String> output = new LinkedList<String>();
    	output.add(name);
    	output.add(closingDate.toString());
    	String temp = "";
    	for (Bet b : betsList)
    		temp += b.toString();
    	output.add(temp);
    	String temp2 = "";
    	for (Competitor c : competitorsList)
    		temp2 += c.toString();
    	output.add(temp2);
    	return output;
    }

    public void settlePodium(Competitor first, Competitor second, Competitor third) {
        Iterator<Bet> it = betsList.iterator();
        while (it.hasNext()) {
	        Bet bet = it.next();
	        if (bet instanceof BetPodium) {
	            Subscriber subscriber = bet.getSubscriber();
	            if (bet.getCompetitors().get(0).equals(first) && bet.getCompetitors().get(1).equals(second) && bet.getCompetitors().get(2).equals(third)) {
	                try {
	                    subscriber.creditSubscriber(bet.getNumberTokens());
	                } catch (BadParametersException e) {
	                    e.printStackTrace();
	                }
	            }
                try {
                    subscriber.removeBet(bet);
                } catch (ExistingBetException e) {
                    e.printStackTrace();
                }
            }
        }    
    }

    public void settleWinner(Competitor winner) {
        Iterator<Bet> it = betsList.iterator();
        while (it.hasNext()) {
            Bet bet = it.next();
            if (bet instanceof BetWinner) {
	            Subscriber subscriber = bet.getSubscriber();
	            if (bet.getCompetitors().get(0).equals(winner)) {
	                try {
	                    subscriber.creditSubscriber(bet.getNumberTokens());
	                } catch (BadParametersException e) {
	                    e.printStackTrace();
	                }
	            }
                try {
                    subscriber.removeBet(bet);
                } catch (ExistingBetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void addCompetitor(Competitor c) throws ExistingCompetitorException{
    	if (competitorExist(c))
    		throw new ExistingCompetitorException();
    	else
    		competitorsList.add(c);
    }

    public void deleteCompetitor (Competitor competitor) throws CompetitionException, ExistingCompetitorException {
        if (this.competitionEnded() || (this.competitorsList.size()<=2)) throw new CompetitionException();
        if (!(this.competitorExist(competitor)))
            throw new ExistingCompetitorException();
        for (Bet bet : betsList) {
            if (bet.getCompetitors().contains(competitor)){
                try {
                    bet.getSubscriber().cancelBet(bet);
                } catch (ExistingBetException e) {
                    e.printStackTrace();
                } catch (BadParametersException e) {
                    e.printStackTrace();
                }
                this.removeBet(bet);
            }
        }
        this.removeCompetitor(competitor);
        competitor.removeCompetition(this);
    }

    public void cancelAllBets() {
        Iterator<Bet> it = betsList.iterator();
        while (it.hasNext()) {
            Bet bet = it.next();
            try {
                bet.getSubscriber().cancelBet(bet);
            } catch (ExistingBetException e) {
                e.printStackTrace();
            } catch (BadParametersException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (getClass() != o.getClass())
            return false;
        Competition c = (Competition) o;
        return c.name == name;
    }

    private void removeCompetitor (Competitor competitor) {
        this.competitorsList.remove(competitor);

    }

    public void removeBet (Bet bet) {
        this.betsList.remove(bet);
    }

    public HashSet<Competitor> getCompetitors() {
    	return competitorsList;
    }
   public ArrayList<Competitor> getWinners(){
	   return winners;
   }
   public HashSet<Bet> getBets(){
	   return betsList;
   }
}

