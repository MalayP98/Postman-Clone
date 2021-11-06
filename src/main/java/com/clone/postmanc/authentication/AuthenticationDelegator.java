package com.clone.postmanc.authentication;

import com.fasterxml.jackson.databind.JsonNode;
import javassist.NotFoundException;

public class AuthenticationDelegator implements Authentication {

  private Authentication authentication;

  public AuthenticationDelegator(AuthType type, JsonNode authenticationDetail)
      throws NotFoundException {
    switch (type) {
      case NOAUTH:
        this.authentication = new Authentication() {
          @Override
          public String getAuthentication() {
            return null;
          }

          @Override
          public void resolveAuthentication(JsonNode authentication) {
          }
        };
        break;
      case API_KEY:
        this.authentication = new ApiKey(authenticationDetail);
        break;
      case BASIC:
        this.authentication = new BasicAuthentication(authenticationDetail);
        break;
      case BEARER:
        this.authentication = new BearerAuthentication(authenticationDetail);
        break;
      default:
        throw new NotFoundException("No authentication method found for the call.");
    }
  }

  @Override
  public String getAuthentication() {
    return this.authentication.getAuthentication();
  }

  @Override
  public void resolveAuthentication(JsonNode authentication) {
  }
}
