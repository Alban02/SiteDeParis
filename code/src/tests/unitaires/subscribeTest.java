package tests.unitaires;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import fr.uv1.bettingServices.*;

import fr.uv1.bettingServices.exceptions.AuthenticationException;
import fr.uv1.bettingServices.exceptions.BadParametersException;
import fr.uv1.bettingServices.exceptions.ExistingSubscriberException;
import fr.uv1.bettingServices.exceptions.SubscriberException;

class subscribeTest {

	@Test
	void test1() throws AuthenticationException, ExistingSubscriberException, SubscriberException, BadParametersException {
		
		BettingSite test = new BettingSite();
		
		// Tous les paramètres sont bien rentrés
		System.out.println("-------------- Test de subscribe un joueur ---------------------");
		System.out.println("On entre tous les bons paramètres.");
		test.subscribe(new String("Arthus"), new String("ANIN"), new String("Eric1er"), new String("14/05/1977"), test.getManagerPassword());
		System.out.println(">>>>>>>>>>>>  Test réussi --------------------------------------------\n");
	}
	
	@Test
	void test2() throws AuthenticationException, ExistingSubscriberException, SubscriberException, BadParametersException {
		
		BettingSite test = new BettingSite();
		
		// Paramètre mal instancié
		System.out.println("-------------- Test de subscribe un joueur ---------------------");
		System.out.println("On entre un espace pour le lastname du joueur.");
		test.subscribe(new String(" "), new String("ANIN"), new String("Eric1er"), new String("14/05/1977"), test.getManagerPassword());
		System.out.println(">>>>>>>>>>>>  Test échoué --------------------------------------------\n");
	}
	
	@Test
	void test3() throws AuthenticationException, ExistingSubscriberException, SubscriberException, BadParametersException {
		
		BettingSite test = new BettingSite();
		
		// Paramètre mal instancié
		System.out.println("-------------- Test de subscribe un joueur ---------------------");
		System.out.println("On entre un seul caractère (tiret ou underscore) pour le lastname du joueur.");
		test.subscribe(new String("_"), new String("ANIN"), new String("Eric1er"), new String("14/05/1977"), test.getManagerPassword());
		System.out.println(">>>>>>>>>>>>  Test échoué --------------------------------------------\n");
	}
	
	@Test
	void test4() throws AuthenticationException, ExistingSubscriberException, SubscriberException, BadParametersException {
		
		BettingSite test = new BettingSite();
		
		// Paramètre mal instancié
		System.out.println("-------------- Test de subscribe un joueur ---------------------");
		System.out.println("On donne une année de naissance de tel sorte que le joueur soit mineur.");
		test.subscribe(new String("_"), new String("ANIN"), new String("Eric1er"), new String("14/05/3000"), test.getManagerPassword());
		System.out.println(">>>>>>>>>>>>  Test échoué --------------------------------------------\n");
	}
}
