package com.cred.exceptions;

public class FeatureToggleBaseException extends Exception{
 Object request;
 Object response;

  public FeatureToggleBaseException(String message) {
    super(message);
  }

  public FeatureToggleBaseException(String message, Object request) {
    super(message);
    this.request = request;
  }

  public FeatureToggleBaseException(String message, Object request, Object response) {
    super(message);
    this.request = request;
    this.response = response;
  }

  public Object getRequest() {
    return request;
  }

  public Object getResponse() {
    return response;
  }

}
