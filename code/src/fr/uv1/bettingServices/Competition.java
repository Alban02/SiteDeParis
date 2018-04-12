package fr.uv1.bettingServices;

import fr.uv1.bettingServices.exceptions.CompetitionException;
import fr.uv1.bettingServices.exceptions.ExistingCompetitorException;

import java.util.HashSet;

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
        competitorsList = new HashSet<Competitior>();
        betsList = new HashSet<Bet>();
    }

    private boolean competitionEnded (){
        return closingDate.isInThePast();
    }

    private boolean competitorExist (Competitor competitor) {
        return competitorsList.contain(competitor);
    }

    @Override
    public String toString() {
    
    }

    public void settlePodium(Competitor first, Competitor second, Competitor third) {
        Iterator it = betsList.iterator();
        while (it.hasNext()) {
            Bet bet = it.next();
            Subscriber subscriber = bet.getSubscriber();
            if (bet.getCompetitor().get(0).equals(first) && bet.getCompetitor().get(1).equals(second) && bet.getCompetitor().get(2).equals(third)) {
                subscriber.creditSubscriber(bet.getStake());
            }
            subscriber.removeBet(bet);
        }    
    }

    public void settleWinner(Competitor winner) {
        Iterator it = betsList.iterator();
        while (it.hasNext()) {
            Bet bet = it.next();
            Subscriber subscriber = bet.getSubscriber();
            if (bet.getCompetitor().get(0).equals(winner)) {
                subscriber.creditSubscriber(bet.getStake());
            }
            subscriber.removeBet(bet);
        }
    }

    public void addCompetitor(Competitor c) {
        competitorsList.add(c);
    }

    public void deleteCompetitor (Competitor competitor) throws  CompetitionException, ExistingCompetitorException {
        if (this.competitionEnded())
            throw new CompetitionException();
        if (!(this.competitorExist(competitor)))
            throw new ExistingCompetitorException();
        competitorsList.remove(competitor);
    }

    public cancelAllBets() {
        Iterator it = betsList.iterator();
        while (it.hasNext()) {
            Bet bet = it.next();
            bet.getSubscriber().cancelBet(bet);
        }
    }

    public boolean equals(Object o) {
        if (this == 0)
            return true;
        if (o == null)
            return false;
        if (getClass() != o.getClass())
            return false;
        Competition c = (Competition) o;
        return c.name == name;
    }


}

