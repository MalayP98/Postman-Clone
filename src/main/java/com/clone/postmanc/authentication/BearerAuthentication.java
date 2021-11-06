package com.clone.postmanc.authentication;

import com.clone.postmanc.utils.AppConstants;
import com.fasterxml.jackson.databind.JsonNode;

public class BearerAuthentication implements Authentication {

  private final String BEARER = "Bearer";

  private final String TOKEN = "token";

  public String token;

  public BearerAuthentication(JsonNode authentication) {
    resolveAuthentication(authentication);
  }

  @Override
  public String getAuthentication() {
    return BEARER + AppConstants.SPACE + token;
  }

  @Override
  public void resolveAuthentication(JsonNode authentication) {
    this.token = authentication.get(TOKEN).asText();
  }
}
