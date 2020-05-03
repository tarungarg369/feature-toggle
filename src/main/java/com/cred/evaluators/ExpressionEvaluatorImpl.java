package com.cred.evaluators;

import com.cred.exceptions.FeatureToggleBaseException;
import com.cred.exceptions.InvalidExpressionException;
import com.cred.operators.ExactlyEqualTo;
import com.cred.operators.GreaterThan;
import com.cred.operators.GreaterThanEqual;
import com.cred.operators.LessThan;
import com.cred.operators.LessThanEqual;
import com.cred.operators.LogicalAND;
import com.cred.operators.LogicalNOT;
import com.cred.operators.LogicalOR;
import com.cred.operators.LogicalOperator;
import com.cred.operators.NotEqualTo;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class ExpressionEvaluatorImpl implements ExpressionEvaluator {

  private static ExpressionEvaluator expressionEvaluator = null;
  private static Set<LogicalOperator> operators;
  private static HashMap<String, LogicalOperator> symbolOperatorMap;

  public ExpressionEvaluatorImpl() {
    populateOperators();
  }

  public static ExpressionEvaluator getInstance() {
    if (expressionEvaluator == null)
      expressionEvaluator = new ExpressionEvaluatorImpl();
    return expressionEvaluator;
  }

  private static boolean isOperator(String str) {
    for (String key : symbolOperatorMap.keySet()) {
      if (str.equalsIgnoreCase(key))
        return true;
    }
    return false;
  }

  private static int getOperatorPrecedence(String str) {
    if (symbolOperatorMap.containsKey(str)) {
      return symbolOperatorMap.get(str).getPrecedence();
    } else {
      return -1;
    }
  }

  private static List<Object> reverseOperandList(List<Object> operandList) {
    int length = operandList.size();
    List<Object> reversedList = new ArrayList<Object>();
    for (int i = length - 1; i >= 0; i--)
      reversedList.add(operandList.get(i));
    return reversedList;
  }

  private void populateOperators() {
    operators = new HashSet();
    operators.add(ExactlyEqualTo.getInstance());
    operators.add(GreaterThan.getInstance());
    operators.add(GreaterThanEqual.getInstance());
    operators.add(LessThan.getInstance());
    operators.add(LessThanEqual.getInstance());
    operators.add(LogicalAND.getInstance());
    operators.add(LogicalNOT.getInstance());
    operators.add(LogicalOR.getInstance());
    operators.add(NotEqualTo.getInstance());
    symbolOperatorMap = createSymbolMap(operators);
  }

  private HashMap<String, LogicalOperator> createSymbolMap(Set<LogicalOperator> operators) {
    HashMap<String, LogicalOperator> hashMap = new HashMap<String, LogicalOperator>();

    for (LogicalOperator operator : operators) {
      List<String> symbols = operator.getSymbols();
      for (String symbol : symbols) {
        hashMap.put(symbol, operator);
      }
    }
    return hashMap;
  }

  public String infixToPostfix(String exp) throws InvalidExpressionException {
    try {
      String result = new String("");

      Stack<String> stack = new Stack<String>();
      String[] elements = exp.split(" ");

      for (String element : elements) {
        if (element.equalsIgnoreCase("("))
          stack.push(element);

        else if (element.equalsIgnoreCase(")")) {
          while (!stack.isEmpty() && !stack.peek().equalsIgnoreCase("("))
            result = result + stack.pop() + " ";

          if (!stack.isEmpty() && !stack.peek().equalsIgnoreCase("("))
            throw new InvalidExpressionException(exp);
          else
            stack.pop();
        } else if (!isOperator(element)) {
          result = result + element + " ";
        } else {
          while (!stack.isEmpty() && getOperatorPrecedence(element) <= getOperatorPrecedence(stack.peek())) {
            if (stack.peek().equalsIgnoreCase("("))
              throw new InvalidExpressionException(exp);
            result = result + stack.pop() + " ";
          }
          stack.push(element);
        }
      }
      while (!stack.isEmpty()) {
        if (stack.peek().equalsIgnoreCase("("))
          return "Invalid Expression";
        result = result + stack.pop() + " ";
      }
      return result;
    }catch (EmptyStackException ex) {
      throw new InvalidExpressionException(exp);
    }

  }


  private Object getValue(String key,Map<String,Object> contextMap) {
    if(contextMap.containsKey(key))
      return contextMap.get(key);
    return null;
  }


  public boolean postfixEvaluator(String postfixExpression, Map<String, Object> contextMap) throws FeatureToggleBaseException {
    Stack<Object> stack=new Stack<Object>();

    String[] elements = postfixExpression.split(" ");

    for (String element : elements)
    {
      if(isOperator(element))
      {
        List<Object> operandList = new ArrayList<Object>();
        LogicalOperator logicalOperator = symbolOperatorMap.get(element);
        for(int i=1; i<=logicalOperator.getOperandCount();i++){
          operandList.add(stack.pop());
        }
        operandList = reverseOperandList(operandList);
        stack.push(logicalOperator.operate(operandList));
      }
      else {
        Object value = getValue(element,contextMap);
        if(value!=null) {
          stack.push(value);
        }
        else if(element.equalsIgnoreCase("true"))
          stack.push(true);
        else if(element.equalsIgnoreCase("false"))
          stack.push(false);
        else {
          try {
            int val = Integer.parseInt(element);
            stack.push(val);
            continue;
          }catch (NumberFormatException nfe){ }
          try {
            double val = Double.parseDouble(element);
            stack.push(val);
            continue;
          }catch (NumberFormatException nfe){ }
          stack.push(element);
        }
      }
    }
    return (Boolean)stack.pop();
  }

  public boolean infixEvaluator(String infixExpression, Map<String, Object> contextMap) throws FeatureToggleBaseException{
    return postfixEvaluator(infixToPostfix(infixExpression).replace("(", "").replace(")",""), contextMap);
  }
}
