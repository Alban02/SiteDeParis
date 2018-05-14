package competitor;

import fr.uv1.bettingServices.Betting;
import fr.uv1.bettingServices.BettingSite;
import fr.uv1.bettingServices.Competitor;
import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CompetitorTest extends ValidationTest{
    private ArrayList<Competitor> competitors;
    private ArrayList<Competitor> competitorTeams;
    private Competitor pc1, pc2, pc3, madrid, barca;

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        // Code exécuté après l'exécution de tous les tests
    }

    @Before
    public void setUp() throws Exception {
        this.setBetting(this.plugToBetting());
        // Code exécuté avant chaque test
    }

    @After
    public void tearDown() throws Exception {
        // Code exécuté après chaque test
    }

    @Test
    public void hasValidName() {
        testTeamWithCompetitorsOK();
    }

    @Test
    public void addMember() {
    }

    @Test
    public void deleteMember() {
    }

    @Test
    public void getCompetitions() {
    }

    @Test
    public void getMembers() {
    }

    @Test
    public void addCompetition() {
    }

    @Test
    public void removeCompetition() {
    }

    @Test
    public void sameName() {
    }

    @Test
    public void sameName1() {
    }

    @Test
    public void same() {
    }


    public void setUpTeamWithCompetitors() {
        try {
            pc1 = this.getBetting().createCompetitor(new String("Durant"),
                    new String("Miguel"), new String("20-07-1984"),
                    this.getManagerPassword());
            pc2 = this.getBetting().createCompetitor(
                    new String("Duranto"), new String("Miguel"),
                    new String("13-12-1983"), this.getManagerPassword());

            madrid = this.getBetting().createCompetitor(
                    new String("Madrid"), this.getManagerPassword());
            madrid.addMember(pc1);
            madrid.addMember(pc2);

            pc3 = this.getBetting().createCompetitor(
                    new String("Durante"), new String("Miguel"),
                    new String("20-07-1980"), this.getManagerPassword());

            barca = this.getBetting().createCompetitor(
                    new String("Barca"), this.getManagerPassword());
            barca.addMember(pc3);

            competitorTeams = new ArrayList<Competitor>();
            competitorTeams.add(madrid);
            competitorTeams.add(barca);
        } catch (Exception e) {
            assert (false);
        }
    }


    public void testTeamWithCompetitorsOK() {
        try {
            this.setUpTeamWithCompetitors();
        } catch (Exception e) {
            System.out
                    .println("Ajout d'une compétition valide (otra_compet,15-08-2018) a levé l'exception "
                            + e.getClass());
        }

    }


    @Override
    public Betting plugToBetting() {
        return new BettingSite();
    }

    @Override
    public String getManagerPassword() {
        return "password";
    }
}