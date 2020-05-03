package com.cred.inputprocessor;

import com.cred.exceptions.FeatureToggleBaseException;

import java.io.BufferedReader;
import java.io.IOException;

public interface Processor {

  InputParameters existingUserProcessor() throws IOException , FeatureToggleBaseException;
  InputParameters newUserProcessor(BufferedReader reader) throws IOException , FeatureToggleBaseException;
}
