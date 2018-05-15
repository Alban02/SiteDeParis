package bet;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import fr.uv1.bettingServices.* ;
import fr.uv1.utils.MyCalendar;

import org.junit.* ;


public class BetWinnerTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
			try {
				MyCalendar.setDate(2011, 8, 1);
				System.out.println("Nous sommes au " + MyCalendar.getDate());

				// Suppose subscribe and addCompetition ok
				// Suppose createCompetitor ok

				String compName = new String("a_compet");
				winner = Competitor.createCompetitor(
						new String("Durant"),
						new String("Miguel"),
						new SimpleDateFormat("dd-MM-yyyy").format(new MyCalendar(
								1984, 12, 13).getTime()),
						increment.getManagerPassword());

				second = increment.getBetting().createCompetitor(
						new String("Duranto"),
						new String("Miguel"),
						new SimpleDateFormat("dd-MM-yyyy").format(new MyCalendar(
								1983, 12, 13).getTime()),
						increment.getManagerPassword());

				third = increment.getBetting().createCompetitor(
						new String("Duranti"),
						new String("Migueli"),
						new SimpleDateFormat("dd-MM-yyyy").format(new MyCalendar(
								1982, 4, 13).getTime()),
						increment.getManagerPassword());

				// Add a subscriber
				pwd = increment.getBetting().subscribe(new String("Mato"),
						new String("Anna"), new String("salto"), new String("11-03-1987"),
						new String(increment.getManagerPassword()));

				// Add a competition
				competitors = new ArrayList<Competitor>();
				competitors.add(winner);
				competitors.add(second);
				competitors.add(third);

				increment.getBetting().addCompetition(new String("a_compet"),
						new MyCalendar(2016, 2, 1), competitors,
						new String(increment.getManagerPassword()));
			} catch (Exception e) {
				assert (false);
			}
	}

}
