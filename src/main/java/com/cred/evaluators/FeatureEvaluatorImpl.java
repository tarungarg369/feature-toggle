package com.cred.evaluators;

import com.cred.exceptions.FeatureToggleBaseException;

import java.util.Map;

public class FeatureEvaluatorImpl implements FeatureEvaluator {

  private static FeatureEvaluator featureEvaluator = null;

  public static FeatureEvaluator getInstance(){
    if(featureEvaluator == null) featureEvaluator = new FeatureEvaluatorImpl();
    return featureEvaluator;
  }

  public boolean isAllowed(String conditionalExpression, String featureName, Map<String, Object> contextMap) throws FeatureToggleBaseException {
      return ExpressionEvaluatorImpl.getInstance().infixEvaluator(conditionalExpression,contextMap);
  }
}
