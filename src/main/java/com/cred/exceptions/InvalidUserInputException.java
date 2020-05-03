package com.cred.exceptions;

public class InvalidUserInputException extends FeatureToggleBaseException {
    public InvalidUserInputException(String inputString, String inputField){
        super("Invalid input string "+inputString+" -> for input field = "+inputField);
    }
}
