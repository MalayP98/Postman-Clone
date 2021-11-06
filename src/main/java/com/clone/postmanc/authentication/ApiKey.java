package com.clone.postmanc.authentication;

import com.clone.postmanc.utils.AppConstants;
import com.fasterxml.jackson.databind.JsonNode;

public class ApiKey implements Authentication {

  private final String KEY = "key";

  private final String VALUE = "value";

  private String key;

  private String value;

  public ApiKey(JsonNode authentication) {
    resolveAuthentication(authentication);
  }

  @Override
  public String getAuthentication() {
    return key + AppConstants.COLON + value;
  }

  @Override
  public void resolveAuthentication(JsonNode authentication) {
    this.key = authentication.get(KEY).asText();
    this.value = authentication.get(VALUE).asText();
  }
}
