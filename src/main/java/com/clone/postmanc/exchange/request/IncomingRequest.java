package com.clone.postmanc.exchange.request;

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

  private RequestMethod method;

  private String endpoint;

  private String authroization;

  private AuthType authType;

  private BodyType bodyType;

  private Map<String, String> headers;

  @JsonIgnore
  private Map<String, Object> queryParam;

  private Payload payload;

  private JsonNode authenticationDetail;

  Boolean authAtHeader;

  private Long userId;

  public String getEndpoint() {
    return endpoint;
  }

  public void setEndpoint(String endpoint) {
    int index = endpoint.indexOf(AppConstants.QUESTION_MARK);
    if (index > -1) {
      this.endpoint = endpoint.substring(0, index);
      setQueryParam(Helpers.stringToMap(endpoint.substring(index + 1), AppConstants.EQUALS,
          AppConstants.AMPERSAND));
    } else {
      this.endpoint = endpoint;
    }
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

  public Map<String, String> getHeaders() {
    return headers;
  }

  public void setHeaders(Map<String, String> headers) {
    this.headers = headers;
  }

  public Map<String, Object> getQueryParam() {
    return queryParam;
  }

  public void setQueryParam(Map<String, Object> queryParam) {
    this.queryParam = queryParam;
  }

  public Payload getPayload() {
    return payload;
  }

  public void setPayload(Payload payload) {
    this.payload = payload;
  }

  public JsonNode getAuthenticationDetail() {
    return authenticationDetail;
  }

  public void setAuthenticationDetail(JsonNode authenticationDetail) {
    this.authenticationDetail = authenticationDetail;
  }

  public RequestMethod getMethod() {
    return method;
  }

  public void setMethod(RequestMethod method) {
    this.method = method;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Boolean getAuthAtHeader() {
    return authAtHeader;
  }

  public void setAuthAtHeader(Boolean authAtHeader) {
    this.authAtHeader = authAtHeader;
  }
}
