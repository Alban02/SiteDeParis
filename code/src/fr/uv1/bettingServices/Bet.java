package fr.uv1.bettingServices;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Bet {
    ArrayList<Competitor> competitors;
    Subscriber subscriber ;
    long stake;

    public long getStake() {
        return stake;
    }

    public ArrayList<Competitor> getCompetitors() {
        return competitors;
    }

    public Subscriber getSubscriber() {
        return subscriber;
    }
}
