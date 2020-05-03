package com.cred.parser;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ObjectToMapParser {
  public static Map<String, Object> object2Map(Object o) {
    Map<String, Object> ret = new HashMap<String, Object>();
    Class co = o.getClass();
    Method[] cMethods = co.getMethods();
    for (Method m : cMethods) {
      try {
        String getterMethodName = m.getName();
        if (!getterMethodName.contains("get"))
          continue;
        String attributeName = getterMethodName.replace("get", "");
        Object valObject = m.invoke(o);
        ret.put(attributeName.toLowerCase(), valObject);
      } catch (Exception e) {
        continue;
      }
    }
    return ret;
  }

}
