package fr.uv1.bettingServices;

import fr.uv1.bettingServices.exceptions.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

public class BettingSite implements Betting {

    @Override
    public void authenticateMngr(String managerPwd) throws AuthenticationException {
        
    }

    @Override
    public String subscribe(String lastName, String firstName, String username, String borndate, String managerPwd) throws AuthenticationException, ExistingSubscriberException, SubscriberException, BadParametersException {
        return null;
    }

    @Override
    public long unsubscribe(String username, String managerPwd) throws AuthenticationException, ExistingSubscriberException {
        return 0;
    }

    @Override
    public List<List<String>> listSubscribers(String managerPwd) throws AuthenticationException {
        return null;
    }

    @Override
    public void addCompetition(String competition, Calendar closingDate, Collection<Competitor> competitors, String managerPwd) throws AuthenticationException, ExistingCompetitionException, CompetitionException, BadParametersException {

    }

    @Override
    public void cancelCompetition(String competition, String managerPwd) throws AuthenticationException, ExistingCompetitionException, CompetitionException {

    }

    @Override
    public void deleteCompetition(String competition, String managerPwd) throws AuthenticationException, ExistingCompetitionException, CompetitionException {

    }

    @Override
    public void addCompetitor(String competition, Competitor competitor, String managerPwd) throws AuthenticationException, ExistingCompetitionException, CompetitionException, ExistingCompetitorException, BadParametersException {

    }

    @Override
    public Competitor createCompetitor(String lastName, String firstName, String borndate, String managerPwd) throws AuthenticationException, BadParametersException {
        return null;
    }

    @Override
    public Competitor createCompetitor(String name, String managerPwd) throws AuthenticationException, BadParametersException {
        return null;
    }

    @Override
    public void deleteCompetitor(String competition, Competitor competitor, String managerPwd) throws AuthenticationException, ExistingCompetitionException, CompetitionException, ExistingCompetitorException {

    }

    @Override
    public void creditSubscriber(String username, long numberTokens, String managerPwd) throws AuthenticationException, ExistingSubscriberException, BadParametersException {

    }

    @Override
    public void debitSubscriber(String username, long numberTokens, String managerPwd) throws AuthenticationException, ExistingSubscriberException, SubscriberException, BadParametersException {

    }

    @Override
    public void settleWinner(String competition, Competitor winner, String managerPwd) throws AuthenticationException, ExistingCompetitionException, CompetitionException {

    }

    @Override
    public void settlePodium(String competition, Competitor winner, Competitor second, Competitor third, String managerPwd) throws AuthenticationException, ExistingCompetitionException, CompetitionException {

    }

    @Override
    public void betOnWinner(long numberTokens, String competition, Competitor winner, String username, String pwdSubs) throws AuthenticationException, CompetitionException, ExistingCompetitionException, SubscriberException, BadParametersException {

    }

    @Override
    public void betOnPodium(long numberTokens, String competition, Competitor winner, Competitor second, Competitor third, String username, String pwdSubs) throws AuthenticationException, CompetitionException, ExistingCompetitionException, SubscriberException, BadParametersException {

    }

    @Override
    public void changeSubsPwd(String username, String newPwd, String currentPwd) throws AuthenticationException, BadParametersException {

    }

    @Override
    public ArrayList<String> infosSubscriber(String username, String pwdSubs) throws AuthenticationException {
        return null;
    }

    @Override
    public void deleteBetsCompetition(String competition, String username, String pwdSubs) throws AuthenticationException, CompetitionException, ExistingCompetitionException {

    }

    @Override
    public List<List<String>> listCompetitions() {
        return null;
    }

    @Override
    public Collection<Competitor> listCompetitors(String competition) throws ExistingCompetitionException, CompetitionException {
        return null;
    }

    @Override
    public ArrayList<String> consultBetsCompetition(String competition) throws ExistingCompetitionException {
        return null;
    }

    @Override
    public ArrayList<Competitor> consultResultsCompetition(String competition) throws ExistingCompetitionException {
        return null;
    }
}
