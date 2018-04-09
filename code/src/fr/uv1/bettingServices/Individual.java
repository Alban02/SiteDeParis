package fr.uv1.bettingServices;

import fr.uv1.bettingServices.exceptions.BadParametersException;
import fr.uv1.bettingServices.exceptions.ExistingCompetitorException;

public class Individual implements Competitor {

    private String lastName, firstName;

    public Individual(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;

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
