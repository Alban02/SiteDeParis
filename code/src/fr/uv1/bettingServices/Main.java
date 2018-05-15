package fr.uv1.bettingServices;

import fr.uv1.tests.validation.withoutPersistence.AddCompetitionValidationTests;
import fr.uv1.utils.MyCalendar;
import java.util.HashSet;
import fr.uv1.bettingServices.exceptions.*;

public class Main {

	
	public static void main(String[] args) throws BadParametersException, ExistingCompetitionException, AuthenticationException, CompetitionException, ExistingCompetitorException{
		BettingSite b = new BettingSite();
		HashSet<Competitor> c = new HashSet<Competitor>();
		Team best = new Team("Real de madrid boum boum");
		c.add(best);
		Team loser = new Team("Liverpool les gros nuloss");
		c.add(loser);
		b.addCompetition("Champions League", new MyCalendar(2018, 5, 23), c, "azertyuiop");
		System.out.println(b.listCompetitions());
		best.addMember(new Individual("Gronaldo", "Cristiano", "1984/11/20"));
		System.out.println(b.listCompetitions());
		b.cancelCompetition("Champions League", "azertyuiop");
		System.out.println(b.listCompetitions());

	}

}
