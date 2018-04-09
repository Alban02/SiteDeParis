package fr.uv1.bettingServices;

import fr.uv1.bettingServices.exceptions.BadParametersException;
import fr.uv1.bettingServices.exceptions.ExistingCompetitorException;

public class Team implements Competitor {

    private String name;

    public Team(String name) {
    this.name = name;
    }

    @Override
    public boolean hasValidName() {
        return false;
    }

    @Override
    public void addMember(Competitor member) throws ExistingCompetitorException, BadParametersException {

    }

    @Override
    public void deleteMember(Competitor member) throws BadParametersException, ExistingCompetitorException {

    }
}
