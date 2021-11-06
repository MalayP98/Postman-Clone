package com.clone.postmanc.request;

import java.util.Map;
import com.clone.postmanc.authentication.AuthType;
import com.clone.postmanc.utils.AppConstants;
import com.clone.postmanc.utils.Helpers;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * A DTO for Request class.
 */
public class IncomingRequest {

  private String endpoint;

  private String authroization;

  private AuthType authType;

  private BodyType bodyType;

  private Map<String, Object> headers;

  @JsonIgnore
  private Map<String, String> queryParam;

  private String body;

  private JsonNode authenticationDetail;

  private Long userId;

  public String getEndpoint() {
    return endpoint;
  }

  public void setEndpoint(String endpoint) {
    int index = endpoint.indexOf(AppConstants.QUESTION_MARK);
    this.endpoint = endpoint.substring(0, index);
    setQueryParam(Helpers.stringToMap(endpoint.substring(index + 1), AppConstants.EQUALS,
        AppConstants.AMPERSAND));
  }

  public String getAuthroization() {
    return authroization;
  }

  public void setAuthroization(String authroization) {
    this.authroization = authroization;
  }

  public AuthType getAuthType() {
    return authType;
  }

  public void setAuthType(AuthType authType) {
    this.authType = authType;
  }

  public BodyType getBodyType() {
    return bodyType;
  }

  public void setBodyType(BodyType bodyType) {
    this.bodyType = bodyType;
  }

  public Map<String, Object> getHeaders() {
    return headers;
  }

  public void setHeaders(Map<String, Object> headers) {
    this.headers = headers;
  }

  public Map<String, String> getQueryParam() {
    return queryParam;
  }

  public void setQueryParam(Map<String, String> queryParam) {
    this.queryParam = queryParam;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public JsonNode getAuthenticationDetail() {
    return authenticationDetail;
  }

  public void setAuthenticationDetail(JsonNode authenticationDetail) {
    this.authenticationDetail = authenticationDetail;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  @Override
  public String toString() {
    return "IncomingRequest [authType=" + authType + ", authenticationDetail="
        + authenticationDetail + ", authroization=" + authroization + ", body=" + body
        + ", bodyType=" + bodyType + ", endpoint=" + endpoint + ", headers=" + headers + "]";
  }
}
