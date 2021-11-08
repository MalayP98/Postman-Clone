package com.clone.postmanc.exchange.request;

import java.util.Map;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.MappedSuperclass;
import org.springframework.lang.NonNull;

@MappedSuperclass
public class ImmutableRequest {

  @NonNull
  protected RequestMethod method;

  @NonNull
  protected String endpoint;

  protected String authentication;

  @Convert(converter = MapToJsonConverter.class)
  @Column(columnDefinition = "text")
  protected Map<String, String> headers;

  @Convert(converter = MapToJsonConverter.class)
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
