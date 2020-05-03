package com.cred;


import com.cred.evaluators.FeatureEvaluatorImpl;
import com.cred.exceptions.FeatureToggleBaseException;
import com.cred.inputprocessor.AbstractInputProcessor;
import com.cred.inputprocessor.InputParameters;
import com.cred.user.UserContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class FeatureToggleManager {

    public static void main(String[] args) throws FeatureToggleBaseException, IOException {
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
      AbstractInputProcessor processor = new AbstractInputProcessor();
      boolean continueTesting = true;
      while(continueTesting) {
        System.out.println();
        System.out.println("Welcome! Please select any one option to Test functionality\n"
            + "1. Test With Existing User and ConditionExpression \n"
            + "2. Test with New User and ConditionExpression \n"
            + "3. Exit\n");

        String userInput = reader.readLine();
        if (userInput.equals("1")) {
          InputParameters processedInputParameters = processor.existingUserProcessor();
          evaluateFeatureBasedOnUserContext(processedInputParameters);
        } else if (userInput.equals("2")) {
          InputParameters processedInputParameters = processor.newUserProcessor(reader);
          evaluateFeatureBasedOnUserContext(processedInputParameters);
        } else if (userInput.equals("3")) {
          System.out.println("Exiting ......");
          continueTesting = false;
        } else {
          System.out.println("Invalid User choice . Please try again");
        }
      }
    }

    private static void evaluateFeatureBasedOnUserContext(InputParameters processedInputParameters) throws FeatureToggleBaseException{
      Map<String,Object> userContextMap = UserContext.getInstance().getUserContext(processedInputParameters.getUser());
      if(FeatureEvaluatorImpl.getInstance().isAllowed(processedInputParameters.getConditionalExpression(),
          processedInputParameters.getFeatureName(),userContextMap)) {
        System.out.println("Feature is enabled for input user");
      }else
        System.out.println("Feature is disabled for input user");
    }
}
