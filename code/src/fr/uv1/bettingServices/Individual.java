package fr.uv1.bettingServices;

import fr.uv1.bettingServices.exceptions.BadParametersException;
import fr.uv1.bettingServices.exceptions.ExistingCompetitorException;

public class Individual implements Competitor {

    private String lastName, firstName, bornDate;

    public Individual(String lastName, String firstName, String bornDate) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.bornDate = bornDate;


    }

    @Override
    public boolean hasValidName() {
        return true;
    }

    @Override
    public void addMember(Competitor member) throws ExistingCompetitorException, BadParametersException {

    }

    @Override
    public String toString() {
        return "\n***Individual{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", bornDate='" + bornDate + '\'' +
                '}'+"***";
    }

    @Override
    public void deleteMember(Competitor member) throws BadParametersException, ExistingCompetitorException {

    }


    @Override
    public boolean sameName(String name) {
        return false;
    }

    @Override
    public boolean sameName(String lastName, String firstName) {
        return ((this.lastName==lastName)&&(this.firstName==firstName));
    }
}
