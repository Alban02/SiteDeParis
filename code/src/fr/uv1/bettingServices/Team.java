package fr.uv1.bettingServices;

import fr.uv1.bettingServices.exceptions.BadParametersException;
import fr.uv1.bettingServices.exceptions.ExistingCompetitorException;

import java.util.Collection;
import java.util.HashSet;

public class Team implements Competitor {

    private String name;

    public Collection<Competitor> getMembers() {
        return members;
    }

    private Collection<Competitor> members = new HashSet<Competitor>();

    public Team(String name) {
    this.name = name;
    }

    public boolean hasValidName() {
        return true;
    }

    public void addMember(Competitor member) throws ExistingCompetitorException, BadParametersException {
        if ((this.members.contains(member))) throw new ExistingCompetitorException();
        if (!(member.hasValidName())) throw new BadParametersException();
        this.members.add(member);
    }

    public void deleteMember(Competitor member) throws BadParametersException, ExistingCompetitorException {
        if (!(this.members.contains(member))) throw new ExistingCompetitorException();
        if (!(member.hasValidName())) throw new BadParametersException();
        members.remove(member);

    }

    public boolean sameName(String name) {
        return (this.name==name);
    }

    public boolean sameName(String lastName, String firstName) {
        return false;
    }

    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                ", members=" + members +
                '}'+'\n'+"--------------------\n";
    }
    
    public boolean same(Competitor c){
    	Team t = (Team) c;
    	return sameName(t.name);
    }
}
