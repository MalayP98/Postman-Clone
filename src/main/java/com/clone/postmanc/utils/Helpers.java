package com.clone.postmanc.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import com.clone.postmanc.users.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class Helpers {

  public static String getRondomString() {
    return UUID.randomUUID().toString().replace(AppConstants.HYPHEN, AppConstants.EMPTY_STRING)
        .substring(0, 5);
  }

  public static User getPrincipal() {
    return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
  }

  public static Map<String, Object> stringToMap(String str, String keyValueSeprator,
      String elementSeprator) {
    Map<String, Object> result = new HashMap<>();
    for (String element : str.split(elementSeprator)) {
      String[] pair = element.split(keyValueSeprator);
      result.put(pair[0], pair[1]);
    }
    return result;
  }
}
