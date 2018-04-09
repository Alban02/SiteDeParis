package fr.uv1.bettingServices;


public class Manager {

    private String mdp;

    public Manager (String mdp) {
        this.mdp =mdp;
    }

    public boolean authenticateMngr(String mdp) {
        return (this.mdp == mdp) ;
    }


}
