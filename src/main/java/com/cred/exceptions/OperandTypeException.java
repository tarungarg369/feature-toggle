package com.cred.exceptions;


public class OperandTypeException extends FeatureToggleBaseException {
    public OperandTypeException(String logicalOperator, String s){
        super("For the logical operator = "+logicalOperator+" -> "+s);
    }
}