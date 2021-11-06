package com.clone.postmanc.authentication;

import java.util.Base64;
import com.clone.postmanc.utils.AppConstants;
import com.fasterxml.jackson.databind.JsonNode;

public class BasicAuthentication implements Authentication {

  private final String BASIC = "Basic";

  private final String USERNAME = "username";

  private final String PASSWORD = "password";

  private String username;

  private String password;

  public BasicAuthentication(JsonNode authentication) {
    resolveAuthentication(authentication);
  }

  @Override
  public String getAuthentication() {
    String data = username + AppConstants.COLON + password;
    return BASIC + AppConstants.SPACE + Base64.getEncoder().encodeToString(data.getBytes());
  }

  @Override
  public void resolveAuthentication(JsonNode authentication) {
    this.username = authentication.get(USERNAME).asText();
    this.password = authentication.get(PASSWORD).asText();
  }
}
