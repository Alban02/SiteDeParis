package fr.uv1.bettingServices;

public class RunTest {
    public static void main(String[] args) {
        InsideTest insideTest = new InsideTest();
        insideTest.testTeamWithCompetitorsOK();
        System.out
                .println(insideTest.competitorTeams.size());



    }

}
