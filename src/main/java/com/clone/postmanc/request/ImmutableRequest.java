package com.clone.postmanc.request;

import java.util.Map;
import javax.persistence.Convert;
import javax.persistence.MappedSuperclass;
import org.springframework.lang.NonNull;

@MappedSuperclass
public class ImmutableRequest {

  @NonNull
  protected String endpoint;

  protected String authentication;

  @Convert(converter = MapToJsonConverter.class)
  protected Map<String, Object> headers;

  @Convert(converter = MapToJsonConverter.class)
  protected Map<String, String> queryParam;

  protected String body;

  public ImmutableRequest() {

  }

  public ImmutableRequest(Request request) {
    this.endpoint = request.getEndpoint();
    this.authentication = request.getAuthentication();
    this.headers = request.getHeaders();
    this.queryParam = request.getQueryParam();
    this.body = request.getBody();
  }

  public String getEndpoint() {
    return endpoint;
  }

  protected void setEndpoint(String endpoint) {
    this.endpoint = endpoint;
  }

  public String getAuthentication() {
    return authentication;
  }

  protected void setAuthentication(String authentication) {
    this.authentication = authentication;
  }

  public Map<String, Object> getHeaders() {
    return headers;
  }

  protected void setHeaders(Map<String, Object> headers) {
    this.headers = headers;
  }

  public Map<String, String> getQueryParam() {
    return queryParam;
  }

  protected void setQueryParam(Map<String, String> queryParam) {
    this.queryParam = queryParam;
  }

  public String getBody() {
    return body;
  }

  protected void setBody(String body) {
    this.body = body;
  }
}
