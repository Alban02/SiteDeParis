package fr.uv1.bettingServices;

import java.util.regex.*;

import fr.uv1.bettingServices.exceptions.*;

/**
 * 
 * @author Alban GOUGOUA <br>
 * <br>
 * 			This class describes all the attributes and methods that the manager 
 * 			could use on the betting software.<br>
 * 
 * @param mdp
 * 			The password of manager.
 *
 */
public class Manager {

    private String mdp;
    
    /**
     * Manager(String mdp) is the constructor of the class Manager.
     * 
     * @param mdp
     * 			The password of manager.
     * 
     * @throws BadParametersException
     * 			raised if password doesn't respect the syntax of password.
     * 
     */
    public Manager(String mdp) throws BadParametersException {
        
    	boolean check = Pattern.matches("[a-zA-Z0-9]{8,}+", mdp);
    	
    	if(check == false) {
    		try {
    			throw new BadParametersException();
    		}
    		catch(BadParametersException e) {
    			System.out.println(e.pwdIncrorect());
    		}
    	}
    	else {
    		this.mdp = mdp;
    	}
    }
    
    /**
     * checks if the password input matches with the password of manager.
     * 
     * @param mdp
     * 			The password input.
     * 
     * @throws AuthenticationException
     * 			raised if the password input doesn't match with the password of manager.
     * 
     * @throws BadParametersException
     * 			raised if password input doesn't respect the syntax of password.
     * 
     */
    public void authenticateMngr(String mdp) throws AuthenticationException, BadParametersException {
    	
    	boolean check = Pattern.matches("[a-zA-Z0-9]{8,}+", mdp);
    	
    	if(check == false) {
    		try {
    			throw new BadParametersException();
    		}
    		catch(BadParametersException e) {
    			System.out.println(e.pwdIncrorect());
    		}
    	}
    	else {
    		try {
    			if(this.mdp != mdp) throw new AuthenticationException();
    		}
    		catch(AuthenticationException e) {
    			System.out.println(e.getMessage());
    		}
    	}
    }

}
