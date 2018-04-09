package fr.uv1.bettingServices;

import fr.uv1.bettingServices.exceptions.*;

import java.util.*;

public class BettingSite implements Betting {

    Manager manager = new Manager("password");
    Collection<Competitor> listCompetitor = new HashSet<Competitor>();

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

    @Override
    public void authenticateMngr(String managerPwd) throws AuthenticationException {
        if (!(this.manager.authenticateMngr(managerPwd))) throw new AuthenticationException();
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

    @Override
    public Competitor createCompetitor(String lastName, String firstName, String borndate, String managerPwd) throws AuthenticationException, BadParametersException {
        this.authenticateMngr(managerPwd);
        Competitor competitor ;
        competitor = this.findCompetitorByName(lastName,firstName);
        if (competitor == null) {
            competitor = new Individual(lastName,firstName);
            if (!(competitor.hasValidName())) throw new BadParametersException();
            listCompetitor.add(competitor);
        }


        return competitor;
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
    @Override
    public Competitor createCompetitor(String name, String managerPwd) throws AuthenticationException, BadParametersException {
        this.authenticateMngr(managerPwd);
        Competitor competitor ;
        competitor = this.findCompetitorByName(name);
        if (competitor == null) {
            competitor = new Team(name);
            if (!(competitor.hasValidName())) throw new BadParametersException();
            listCompetitor.add(competitor);
        }


        return competitor;
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

    private Competitor findCompetitorByName (String name) {
        return null ;
    }

    private Competitor findCompetitorByName (String lastName, String firstName) {
        return null ;
    }
}
