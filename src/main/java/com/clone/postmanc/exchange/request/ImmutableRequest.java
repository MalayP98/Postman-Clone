package com.clone.postmanc.exchange.request;

import java.util.Map;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import org.springframework.lang.NonNull;

/**
 * The only way to create its object it through sending a request object. This class contains only
 * the essential fields required to call an API. These cannot be set i.e no setter is provided.
 */
@MappedSuperclass
public class ImmutableRequest {

  @NonNull
  @Enumerated(EnumType.STRING)
  protected RequestMethod method;

  @NonNull
  protected String endpoint;

  protected String authentication;

  @Convert(converter = MapToStringVVConverter.class)
  @Column(columnDefinition = "text")
  protected Map<String, String> headers;

  @Convert(converter = MapToStringVVConverter.class)
  protected Map<String, Object> queryParam;

  @Column(columnDefinition = "text")
  protected String body;

  public ImmutableRequest() {

  }

  public ImmutableRequest(Request request) {
    this.method = request.getMethod();
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

  public Map<String, String> getHeaders() {
    return headers;
  }

  protected void setHeaders(Map<String, String> headers) {
    this.headers = headers;
  }

  public Map<String, Object> getQueryParam() {
    return queryParam;
  }

  protected void setQueryParam(Map<String, Object> queryParam) {
    this.queryParam = queryParam;
  }

  public String getBody() {
    return body;
  }

  protected void setBody(String body) {
    this.body = body;
  }

  public RequestMethod getMethod() {
    return method;
  }

  protected void setMethod(RequestMethod method) {
    this.method = method;
  }
}
