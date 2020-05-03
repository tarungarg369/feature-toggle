package com.cred.user;

import com.cred.parser.ObjectToMapParser;

import java.util.Map;

public class UserContext {
  private static UserContext userContext = null;
  public static UserContext getInstance(){
    if(userContext==null) userContext = new UserContext();
    return userContext;
  }



  public Map<String, Object> getUserContext(Object o) {
    Map<String , Object> contextMap = ObjectToMapParser.object2Map(o);
    return contextMap;
  }




}
