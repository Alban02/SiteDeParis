package fr.uv1.bettingServices;

import fr.uv1.bettingServices.exceptions.BadParametersException;
import fr.uv1.bettingServices.exceptions.CompetitionException;
import fr.uv1.bettingServices.exceptions.ExistingCompetitorException;
import fr.uv1.bettingServices.exceptions.SubscriberException;
import fr.uv1.utils.MyCalendar;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class Competition {

    HashSet<Competitor> competitorsList;
    HashSet<Bet> betsList;
    int competitionNumber;
    String name;
    MyCalendar closingDate;

    public Competition(int competitionNumber, String name, MyCalendar closingDate) {
        this.competitionNumber = competitionNumber;
        this.name = name;
        this.closingDate = closingDate;
        competitorsList = new HashSet<Competitor>();
        betsList = new HashSet<Bet>();
    }

    private boolean competitionEnded (){
        return closingDate.isInThePast();
    }

    private boolean competitorExist (Competitor competitor) {
        return competitorsList.contains(competitor);
    }

    @Override
    public String toString() {
    return null;
    }

    public void settlePodium(Competitor first, Competitor second, Competitor third) {
        Iterator it = betsList.iterator();
        while (it.hasNext()) {
            Bet bet = (Bet)it.next();
            Subscriber subscriber = bet.getSubscriber();
            if (bet.getCompetitors().get(0).equals(first) && bet.getCompetitors().get(1).equals(second) && bet.getCompetitors().get(2).equals(third)) {
                try {
                    subscriber.creditSubscriber(bet.getStake());
                } catch (SubscriberException e) {
                    e.printStackTrace();
                } catch (BadParametersException e) {
                    e.printStackTrace();
                }

            }
            subscriber.removeBet(bet);
        }    
    }

    public void settleWinner(Competitor winner) {
        Iterator it = betsList.iterator();
        while (it.hasNext()) {
            Bet bet = (Bet)it.next();
            Subscriber subscriber = bet.getSubscriber();
            if (bet.getCompetitors().get(0).equals(winner)) {
                try {
                    subscriber.creditSubscriber(bet.getStake());
                } catch (SubscriberException e) {
                    e.printStackTrace();
                } catch (BadParametersException e) {
                    e.printStackTrace();
                }
            }
            subscriber.removeBet(bet);
        }
    }

    public void addCompetitor(Competitor c) {
        competitorsList.add(c);
    }

    public void deleteCompetitor (Competitor competitor) throws  CompetitionException, ExistingCompetitorException {
        if (this.competitionEnded() || (this.competitorsList.size()<=2)) throw new CompetitionException();
        if (!(this.competitorExist(competitor)))
            throw new ExistingCompetitorException();
        for (Bet bet : betsList) {
            if (bet.getCompetitors().contains(competitor)){
                bet.getSubscriber().cancelBet(bet);
                this.removeBet(bet);
            }
        }
        this.removeCompetitor(competitor);
    }

    public void cancelAllBets() {
        Iterator it = betsList.iterator();
        while (it.hasNext()) {
            Bet bet = (Bet)it.next();
            bet.getSubscriber().cancelBet(bet);
        }
    }

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

    private  void removeBet (Bet bet) {
        this.betsList.remove(bet);
    }


}

