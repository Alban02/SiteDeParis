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

    public boolean hasValidName() {
        return true;
    }

    public void addMember(Competitor member) throws ExistingCompetitorException, BadParametersException {

    }

    public String toString() {
        return "\n***Individual{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", bornDate='" + bornDate + '\'' +
                '}'+"***";
    }

    public void deleteMember(Competitor member) throws BadParametersException, ExistingCompetitorException {

    }

    public boolean sameName(String name) {
        return false;
    }

    public boolean sameName(String lastName, String firstName) {
        return ((this.lastName==lastName)&&(this.firstName==firstName));
    }
    
    public boolean same(Competitor c) {
    	Individual i = (Individual) c;
    	return ((lastName==i.lastName)&&(firstName==i.firstName)&&(bornDate.toString()==i.bornDate.toString()));
    }
}
