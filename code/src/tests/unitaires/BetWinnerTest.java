package tests.unitaires;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import fr.uv1.bettingServices.* ;
import fr.uv1.bettingServices.exceptions.AuthenticationException;
import fr.uv1.bettingServices.exceptions.BadParametersException;
import fr.uv1.bettingServices.exceptions.ExistingSubscriberException;
import fr.uv1.utils.MyCalendar;

import org.junit.Test;

public class BetWinnerTest {

	@Test
	public void settleWinnerTest() throws AuthenticationException, ExistingSubscriberException, BadParametersException{
		
			BettingSite test = new BettingSite();
			
			//cas nominal 
			System.out.println("-------------- Test de subscribe un joueur ---------------------");
			Subscriber subs = new Subscriber("Alban", "GOUGOUA", "NABLA", "MonbonPetit");
			
			//competition setup
				///competition name
			String competitionName = new String("marathon");
				////competitors setup
			Competitor competitor1 = new Team("Real");
			Competitor competitor2 = new Team("Barça");
			ArrayList<Competitor> competitors = new ArrayList<Competitor>() ;
			competitors.add(competitor1);
			competitors.add(competitor2);
				////dateClosed for competition
			MyCalendar dateClosed =new MyCalendar(2018, 5, 20);
			Competition comp = new Competition("LIGUE 1",dateClosed,competitors);
			
			

		
	}

}
