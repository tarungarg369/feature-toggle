package com.cred.operators;


import com.cred.exceptions.FeatureToggleBaseException;

import java.util.List;

public interface LogicalOperator {
    int getPrecedence();
    int getOperandCount();
    List<String> getSymbols();
    <E> boolean operate(List<E> operands) throws FeatureToggleBaseException;
}
