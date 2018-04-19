package fr.uv1.bettingServices.exceptions;

public class BadParametersException extends Exception {
	
	public String pwdIncrorect() {
		
		return "La syntaxe du mot de passe entré est incorrect.\nLe mot de passe doit avoir 8 caractères minimum"
				+ " et doit être constitué de chiffres et de lettres uniquement.";
	}
	
	public String tokensNumberIncorrect() {
		
		return "Le nombre de jetons entré est incorrect car soit il est négatif (< 0) soit nul (égale à 0).";
	}
}
