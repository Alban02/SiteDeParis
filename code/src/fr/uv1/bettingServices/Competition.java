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
    
    /**@author Tom DAUVE Vincent LOPES
     * @method Competiton
     * @param name
     * @param closingDate
     */
    public Competition(String name, Calendar closingDate) {
        this.name = name;
        MyCalendar c = (MyCalendar) closingDate;
        this.closingDate = c;
        competitorsList = new HashSet<Competitor>();
        betsList = new HashSet<Bet>();
        winners = new ArrayList<Competitor>();
    }
    /**@author Tom DAUVE Vincent LOPES
     * @method Competiton
     * @param name
     * @param closingDate
     * @param competitors
     */
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
    /**@author Tom DAUVE Vincent LOPES
     * @method competitorExist
     * @param competitor
     * @return
     */
    private boolean competitorExist (Competitor competitor) {
        return competitorsList.contains(competitor);
    }

    @Override
    public String toString() {
    	return null;
    }
    /**@author Tom DAUVE Vincent LOPES
     * @method information
     * @return
     */
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
    /**@author Tom DAUVE Vincent LOPES
     * @method settlePodium
     * @param first
     * @param second
     * @param third
     */
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
    /**@author Tom DAUVE Vincent LOPES
     * @method settleWinner
     * @param winner
     */
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
    /** @author Tom DAUVE Vincent LOPES
     * method deleteCompetitor
     * @param competitor 
     */
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
        try {
            competitor.removeCompetition(this);
        } catch (BadParametersException e) {
            e.printStackTrace();
        }
    }
    /**@author Tom DAUVE Vincent LOPES
     * @method
     */
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
	/**@author Tom DAUVE Vincent LOPES
	 * @method removeCompetitor
	 * @param competitor
	 */
    private void removeCompetitor (Competitor competitor) {
        this.competitorsList.remove(competitor);
    }
    /**@author Tom DAUVE Vincent LOPES
     * @method removeBet
     * @param bet
     */
    public void removeBet (Bet bet) {
        this.betsList.remove(bet);
    }

    /**@author Tom DAUVE Vincent LOPES
     * @method addBet
     * @param bet
     */
    public void addBet (Bet bet) {
        this.betsList.add(bet);
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
   /**@author Tom DAUVE Vincent LOPES
    * @method cancelAllBetsTest
    * @return
    * @throws BadParametersException
    * @throws CompetitionException
    */
   public static void main() throws BadParametersException, CompetitionException, ExistingCompetitorException{
	   
	    BettingSite b = new BettingSite();
		HashSet<Competitor> c = new HashSet<Competitor>();
		Team best = new Team("Real de madrid boum boum");
		Team loser = new Team("Liverpool les gros nuloss");
		Team spectator = new Team("Qatar Saint Germain");
		Competition comp = new Competition("champions league",new MyCalendar(2018, 5, 23),c);
		c.add(best);
		if(comp.competitorsList.size()==1)
			System.out.println("addCompetitor test passed");
		else
			System.out.println("addCompetitor test failed");
		c.add(loser);
		c.add(spectator);
		c.remove(spectator);
		if(comp.competitorsList.size()==2)
			System.out.println("removeCompetitor test passed");
		else
			System.out.println("removeCompetitor test failed");
		
		Subscriber sub = new Subscriber("last", "first", "username", "password");
		Bet bet = new BetWinner(100, sub, comp, best);
		comp.addBet(bet);
		if(comp.betsList.size()==1)
			System.out.println("addBet test passed");
		else
			System.out.println("addBet test failed");
		comp.removeBet(bet);
		if(comp.betsList.isEmpty()){
			System.out.println("removeBet test passed");
		}
		else{
			System.out.println("removeBet test failed");
		}
		
		comp.cancelAllBets();
		if(comp.betsList.isEmpty()){
			System.out.println("cancelAllBets test passed");
		}
		else{
			System.out.println("cancelAllBets test failed");
		}
		c.add(spectator);
		try{
			comp.deleteCompetitor(spectator);
			System.out.println("deleteCompetitor test failed");
		}catch(ExistingCompetitorException e){}
		try{
			comp.deleteCompetitor(loser);
			System.out.println("deleteCompetitor test failed");
		}catch(CompetitionException e){System.out.println("deleteCompetitor test passed");} catch(ExistingCompetitorException e){}
		comp.addBet(bet);
		comp.settleWinner(best);
		if (sub.getNumberTokens()==100 ){
			System.out.println("settleWinner test passed");
		}
		else {
			System.out.println("settleWinner test failed");
		}
		c.add(spectator);
		Bet bet2 = new BetPodium(100, sub, comp, best, loser, spectator);
		comp.addBet(bet2);
		comp.settlePodium(best, loser, spectator);
		if (sub.getNumberTokens()==200 ){
			System.out.println("settlePodium test passed");
		}
		else {
			System.out.println("settlePodium test failed");
		}
		comp.deleteCompetitor(spectator);
		if (!comp.competitorExist(spectator)&& comp.competitorExist(best)){
			System.out.println("competitorExist test passed");
		}
		else{
			System.out.println("competitorExist test failed");
		}
		Competition comp2 = new Competition("ligue europa",new MyCalendar(2018, 1, 1),c);
		if (comp2.competitionEnded()&& !comp.competitionEnded()){
			System.out.println("competitionEnded test passed");
		}
		else{
			System.out.println("competitionEnded test failed");
		}
			

   }
}

