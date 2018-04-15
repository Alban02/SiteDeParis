package fr.uv1.bettingServices;

import fr.uv1.bettingServices.exceptions.BadParametersException;
import fr.uv1.bettingServices.exceptions.ExistingCompetitorException;

public class RunTest {
    public static void main(String[] args) {
        InsideTest insideTest = new InsideTest();
        insideTest.testTeamWithCompetitorsOK();
        System.out
                .println(insideTest.competitorTeams);

        try {
            insideTest.madrid.deleteMember(insideTest.pc2);
        } catch (BadParametersException e) {
            e.printStackTrace();
        } catch (ExistingCompetitorException e) {
            e.printStackTrace();
        }
        System.out
                .println(insideTest.competitorTeams);


    }

}
