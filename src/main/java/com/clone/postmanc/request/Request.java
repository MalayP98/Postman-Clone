package com.clone.postmanc.request;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.clone.postmanc.authentication.AuthenticationDelegator;
import org.json.JSONException;
import org.springframework.lang.NonNull;
import javassist.NotFoundException;

@Entity
public class Request extends ImmutableRequest {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @NonNull
  private long userId;

  public Request() {

  }

  public Request(IncomingRequest incomingRequest) throws JSONException, NotFoundException {
    this.method = incomingRequest.getMethod();
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

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public long getUserId() {
    return userId;
  }

  @Override
  public String toString() {
    return "Request [authroization=" + authentication + ", body=" + body + ", endpoint=" + endpoint
        + ", headers=" + headers + ", id=" + id + ", userId=" + userId + "]";
  }
}
