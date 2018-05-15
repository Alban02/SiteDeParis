package tests.unitaires;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import fr.uv1.bettingServices.*;

import fr.uv1.bettingServices.exceptions.AuthenticationException;
import fr.uv1.bettingServices.exceptions.BadParametersException;
import fr.uv1.bettingServices.exceptions.CompetitionException;
import fr.uv1.bettingServices.exceptions.ExistingCompetitionException;
import fr.uv1.bettingServices.exceptions.ExistingSubscriberException;
import fr.uv1.bettingServices.exceptions.SubscriberException;

class unsubscribeTest {

	@Test
	void test() throws AuthenticationException, ExistingSubscriberException, SubscriberException, BadParametersException, ExistingCompetitionException, CompetitionException {
		
		BettingSite test = new BettingSite();
		
		// Tous les paramètres sont bien rentrés
		System.out.println("-------------- Test de unsubscribe un joueur ---------------------");
		System.out.print("On crée un joueur : ");
		test.subscribe(new String("Arthus"), new String("ANIN"), new String("Eric1er"), new String("14/05/1977"), test.getManagerPassword());
		System.out.println(test.infosSubscriber("Eric1er", test.getManagerPassword()) + "\n");
		
		// On crée deux compétiteurs
		System.out.print("On crée deux compétiteurs.");
		Competitor competitor1 = test.createCompetitor(new String("Observateur"), new String("KOUAKOU"), new String("01/01/1970"), test.getManagerPassword());
		Competitor competitor2 = test.createCompetitor(new String("La Beaute"), new String("ARAFAT"), new String("01/01/1980"), test.getManagerPassword());
		
		// On crée une compétition
		System.out.print("On crée une compétition.");
		Calendar closingDate = Calendar.getInstance();
		test.addCompetition(new String("Ligue 1"), closingDate, competitors, test.getManagerPassword());
		
		// On crée un pari sur la compétition crée pour le joueur
		
		
		
		System.out.println(">>>>>>>>>>>>  Test réussi --------------------------------------------\n");
	}

}
