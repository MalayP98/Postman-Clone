package com.clone.postmanc.utils;

import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class Helpers {

  public String getRondomString() {
    return UUID.randomUUID().toString().replace(AppConstants.HYPHEN, AppConstants.EMPTY_STRING)
        .substring(0, 5);
  }
}
