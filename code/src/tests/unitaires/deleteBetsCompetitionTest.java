package tests.unitaires;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;

import org.junit.Test;

import fr.uv1.bettingServices.* ;

import fr.uv1.bettingServices.exceptions.* ;
import fr.uv1.utils.MyCalendar;

public class deleteBetsCompetitionTest {

	@Test
	public void testDeleteBetsCompetition() throws AuthenticationException,SubscriberException, ExistingSubscriberException, BadParametersException, ExistingCompetitionException, CompetitionException{
		
		BettingSite test = new BettingSite();
		
		System.out.println("-------------- Test de deleteBetsCompetition ---------------------");
		
		System.out.println("-------------- Cas nominal---------------------");
		
		System.out.print("On crée un joueur : ");
		test.subscribe(new String("Bao"), new String("CAIFENG"), new String("tutu"), new String("14/05/1995"), test.getManagerPassword());
		System.out.println(test.infosSubscriber("tutu", test.getManagerPassword()) + "\n");
		
		// création de compétiteurs
		System.out.print("création de deux compétiteurs");
		Competitor competitor1 = test.createCompetitor(new String("Réal Madrid"),test.getManagerPassword());
		Competitor competitor2 = test.createCompetitor(new String("Barça"), test.getManagerPassword());
		ArrayList<Competitor> competitors = new ArrayList<Competitor>() ;
		
		// On crée une compétition
		System.out.print("On crée une compétition.");
		Calendar closingDate =new MyCalendar(2018,5,18);
		test.addCompetition(new String("Ligua"), closingDate, competitors, test.getManagerPassword());		
		
		test.deleteBetsCompetition("Ligua", "tutu","MonbonPetit");
	}
	
}
