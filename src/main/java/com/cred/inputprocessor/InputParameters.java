package com.cred.inputprocessor;

import com.cred.user.User;

public class InputParameters {
  private User user;
  private String featureName;
  private String conditionalExpression;

  public InputParameters(User user,String featureName,String conditionalExpression) {
    this.user = user;
    this.featureName = featureName;
    this.conditionalExpression = conditionalExpression;
  }

  public User getUser() {
    return user;
  }

  public String getFeatureName() {
    return featureName;
  }

  public String getConditionalExpression() {
    return conditionalExpression;
  }

  @Override
  public String toString() {
    return "InputParameters{" +
        "user=" + user +
        ", featureName='" + featureName + '\'' +
        ", conditionalExpression='" + conditionalExpression + '\'' +
        '}';
  }
}
