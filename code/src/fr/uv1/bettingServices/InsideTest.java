package fr.uv1.bettingServices;

import fr.uv1.utils.MyCalendar;

import java.util.ArrayList;


public class InsideTest {
    ArrayList<Competitor> competitors;
    ArrayList<Competitor> competitorTeams;
    Competitor pc1, pc2, pc3, madrid, barca;

    BettingSite bettingSite = new BettingSite();

    public void setUpTeamWithCompetitors() {

        try {
            pc1 = bettingSite.createCompetitor(new String("Durant"),
                    new String("Miguel"), new String("20-07-1984"),
                    bettingSite.getManagerPassword());
            pc2 = bettingSite.createCompetitor(
                    new String("Duranto"), new String("Miguel"),
                    new String("13-12-1983"), bettingSite.getManagerPassword());

            madrid = bettingSite.createCompetitor(
                    new String("Madrid"), bettingSite.getManagerPassword());
            madrid.addMember(pc1);
            madrid.addMember(pc2);

            pc3 = bettingSite.createCompetitor(
                    new String("Durante"), new String("Miguel"),
                    new String("20-07-1980"), bettingSite.getManagerPassword());

            barca = bettingSite.createCompetitor(
                    new String("Barca"), bettingSite.getManagerPassword());
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
}
