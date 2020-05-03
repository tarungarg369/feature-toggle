package com.cred.evaluators;

import com.cred.exceptions.FeatureToggleBaseException;

import java.util.Map;

public interface ExpressionEvaluator {
      boolean postfixEvaluator(String conditionalExpression, Map<String , Object> contextMap) throws FeatureToggleBaseException;
      boolean infixEvaluator(String conditionalExpression, Map<String , Object> contextMap) throws FeatureToggleBaseException;
}
