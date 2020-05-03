package com.cred.operators;

import com.cred.exceptions.FeatureToggleBaseException;
import com.cred.exceptions.OperandCountException;
import com.cred.exceptions.OperandTypeException;

import java.util.ArrayList;
import java.util.List;

public class LogicalNOT implements LogicalOperator {
    private static int precedence;
    private static LogicalNOT logicalNOT = null;
    private List<String> symbols;
    private int operandCount;

    private LogicalNOT(){
        precedence = 3;
        symbols = new ArrayList<String>();
        symbols.add("NOT");
        symbols.add("!");
        symbols.add("not");
        operandCount = 1;
    }

    public static LogicalNOT getInstance(){
        if(logicalNOT == null)
            logicalNOT = new LogicalNOT();
        return logicalNOT;
    }

    public int getOperandCount() {
        return operandCount;
    }

    public int getPrecedence() {
        return precedence;
    }

    public List<String> getSymbols() {
        return symbols;
    }

    public <E> boolean operate(List<E> operands) throws FeatureToggleBaseException {
        if (operands.size() != operandCount)
            throw new OperandCountException("LogicalNOT", operandCount, operands.size());

        E operand = operands.get(0);
        if (operand instanceof Boolean) {
            return !(Boolean)operand;
        } else {
            throw new OperandTypeException("LogicalNOT", "Operand Type Not Supported");
        }
    }
}