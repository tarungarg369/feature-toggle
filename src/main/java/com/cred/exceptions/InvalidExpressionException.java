package com.cred.exceptions;

public class InvalidExpressionException extends FeatureToggleBaseException {
  public InvalidExpressionException(String Expression){
    super("Provided Expression -> "+ Expression+ " is invalid . Please fix and Retry" );
  }
}
