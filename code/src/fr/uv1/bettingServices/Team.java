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

    @Override
    public boolean hasValidName() {
        return true;
    }

    @Override
    public void addMember(Competitor member) throws ExistingCompetitorException, BadParametersException {
        if ((this.members.contains(member))) throw new ExistingCompetitorException();
        if (!(member.hasValidName())) throw new BadParametersException();
        this.members.add(member);
    }

    @Override
    public void deleteMember(Competitor member) throws BadParametersException, ExistingCompetitorException {
        if (!(this.members.contains(member))) throw new ExistingCompetitorException();
        if (!(member.hasValidName())) throw new BadParametersException();
        members.remove(member);

    }


    @Override
    public boolean sameName(String name) {
        return (this.name==name);
    }

    @Override
    public boolean sameName(String lastName, String firstName) {
        return false;
    }

    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                ", members=" + members +
                '}'+'\n'+"--------------------\n";
    }
}
