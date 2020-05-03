package com.cred.exceptions;

public class OperandCountException extends FeatureToggleBaseException {
    public OperandCountException(String logicalOperator, int expectedCount, int receivedCount){
        super("For the logical operator = "+logicalOperator+" -> Expected Number of Operands = "+expectedCount+"; Operands Given = "+receivedCount);
    }
}
