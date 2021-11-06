package com.clone.postmanc.authentication;

import com.fasterxml.jackson.databind.JsonNode;
import org.json.JSONException;

public interface Authentication {

  public void resolveAuthentication(JsonNode authentication) throws JSONException;

  public String getAuthentication();

}
