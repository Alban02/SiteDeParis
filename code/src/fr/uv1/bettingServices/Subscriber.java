package fr.uv1.bettingServices;

public class Subscriber {
    private String lastName, firstName, userName, password ;
    private long tokenNumbers ;

    public Subscriber(String lastName, String firstName, String userName, String password, long tokenNumbers) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.userName = userName;
        this.password = password;
        this.tokenNumbers = tokenNumbers;
    }

    public void creditSubscriber(long stake) {

    }
    //removes bet from the list
    public void removeBet (Bet bet)    {

    }
    //removes bet from the list and refund the subscriber
    public void cancelBet (Bet bet)  {

    }
}
