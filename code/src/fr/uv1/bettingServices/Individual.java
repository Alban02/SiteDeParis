package fr.uv1.bettingServices;

import fr.uv1.bettingServices.exceptions.BadParametersException;
import fr.uv1.bettingServices.exceptions.ExistingCompetitorException;

import java.util.Collection;
import java.util.HashSet;

public class Individual implements Competitor {

    private String lastName, firstName, bornDate;
    private Collection<Competitor> members;
    private Collection<Competition> competitions;

    public Individual(String lastName, String firstName, String bornDate) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.bornDate = bornDate;

        this.members = new HashSet<Competitor>();
        this.competitions = new HashSet<Competition>();
    }

    public boolean hasValidName() {
        return true;
    }
    public Collection<Competitor> getMembers() {
        return members;
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

    public Collection<Competition> getCompetitions() {
        return this.competitions;
    }

    public void addCompetition(Competition competition) {
        this.competitions.add(competition);
    }

    public void removeCompetition(Competition competition) {
        this.competitions.remove(competition);
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
