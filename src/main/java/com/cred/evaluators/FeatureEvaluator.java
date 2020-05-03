package com.cred.evaluators;

import com.cred.exceptions.FeatureToggleBaseException;

import java.util.Map;

public interface FeatureEvaluator {
  boolean isAllowed(String conditionalExpression , String featureName , Map<String , Object> contextMap) throws FeatureToggleBaseException;
}
