package com.clone.postmanc.request;

import java.util.Map;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.clone.postmanc.authentication.AuthenticationDelegator;
import org.json.JSONException;
import org.springframework.lang.NonNull;
import javassist.NotFoundException;

@Entity
public class Request {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @NonNull
  private String endpoint;

  private String authentication;

  @Convert(converter = MapToJsonConverter.class)
  private Map<String, Object> headers;

  @Convert(converter = MapToJsonConverter.class)
  private Map<String, String> queryParam;

  private String body;

  @NonNull
  private long userId;

  public Request() {

  }

  public Request(IncomingRequest incomingRequest) throws JSONException, NotFoundException {
    this.endpoint = incomingRequest.getEndpoint();
    this.authentication = (new AuthenticationDelegator(incomingRequest.getAuthType(),
        incomingRequest.getAuthenticationDetail())).getAuthentication();
    this.headers = incomingRequest.getHeaders();
    this.queryParam = incomingRequest.getQueryParam();
    this.body = incomingRequest.getBody();
    this.userId = incomingRequest.getUserId();
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getEndpoint() {
    return endpoint;
  }

  public void setEndpoint(String endpoint) {
    this.endpoint = endpoint;
  }

  public String getAuthentication() {
    return authentication;
  }

  public void setAuthentication(String authentication) {
    this.authentication = authentication;
  }

  public Map<String, Object> getHeaders() {
    return headers;
  }

  public void setHeaders(Map<String, Object> headers) {
    this.headers = headers;
  }

  public Object getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public long getUserId() {
    return userId;
  }

  public Map<String, String> getQueryParam() {
    return queryParam;
  }

  public void setQueryParam(Map<String, String> queryParam) {
    this.queryParam = queryParam;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((authentication == null) ? 0 : authentication.hashCode());
    result = prime * result + ((body == null) ? 0 : body.hashCode());
    result = prime * result + ((endpoint == null) ? 0 : endpoint.hashCode());
    result = prime * result + ((headers == null) ? 0 : headers.hashCode());
    return result;
  }

  public boolean equals(Object object) {
    if (this == object)
      return true;
    if (object == null)
      return false;
    if (getClass() != object.getClass()) {
      return false;
    }
    Request request = (Request) object;
    if (hashCode() != request.hashCode()) {
      return false;
    }
    return (userId == request.getUserId());
  }

  @Override
  public String toString() {
    return "Request [authroization=" + authentication + ", body=" + body + ", endpoint=" + endpoint
        + ", headers=" + headers + ", id=" + id + ", userId=" + userId + "]";
  }
}
