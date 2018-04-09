package fr.uv1.bettingServices;

import fr.uv1.bettingServices.exceptions.CompetitionException;
import fr.uv1.bettingServices.exceptions.ExistingCompetitorException;

import java.util.Collection;

public class Competition {

    Collection<Competitor> listCompetitors;



    private boolean competitionEnded (){
        return false;
    }

    private boolean competitorExist (Competitor competitor) {
        return false;
    }

    public void deleteCompetitor (Competitor competitor) throws  CompetitionException, ExistingCompetitorException {
        if (this.competitionEnded()) throw new CompetitionException();
        if (!(this.competitorExist(competitor))) throw new ExistingCompetitorException() ;
        this.listCompetitors.remove(competitor);

    }
}
