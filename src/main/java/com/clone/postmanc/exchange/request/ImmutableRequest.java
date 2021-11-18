package com.clone.postmanc.exchange.request;

import java.util.Map;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import com.clone.postmanc.authentication.AuthType;
import com.clone.postmanc.authentication.AuthenticationDelegator;
import org.springframework.lang.NonNull;
import javassist.NotFoundException;

/**
 * The only way to create its object it through sending a request object. This class contains only
 * the essential fields required to call an API. These cannot be set i.e no setter is provided.
 */
@MappedSuperclass
public class ImmutableRequest {

  @NonNull
  @Enumerated(EnumType.STRING)
  private RequestMethod method;

  @NonNull
  private String endpoint;

  private String authentication;

  private BodyType bodyType;

  @Convert(converter = MapToStringVVConverter.class)
  @Column(columnDefinition = "text")
  private Map<String, String> headers;

  @Convert(converter = MapToStringVVConverter.class)
  private Map<String, Object> queryParam;

  @Embedded
  private Payload payload;

  @Transient
  private Boolean authAtHeader;

  public ImmutableRequest() {

  }

  public ImmutableRequest(IncomingRequest incomingRequest) throws NotFoundException {
    this.method = incomingRequest.getMethod();
    this.endpoint = incomingRequest.getEndpoint();
    this.authentication = (new AuthenticationDelegator(incomingRequest.getAuthType(),
        incomingRequest.getAuthenticationDetail())).getAuthentication();
    this.headers = incomingRequest.getHeaders();
    this.queryParam = incomingRequest.getQueryParam();
    this.payload = incomingRequest.getPayload();
    this.bodyType = incomingRequest.getBodyType();
    if ((incomingRequest.getAuthType() != null)
        && (!incomingRequest.getAuthType().equals(AuthType.NOAUTH)))
      this.authAtHeader = incomingRequest.getAuthAtHeader();
    else
      this.authAtHeader = true;
  }

  public String getEndpoint() {
    return endpoint;
  }

  public String getAuthentication() {
    return authentication;
  }

  public Map<String, String> getHeaders() {
    return headers;
  }

  public Map<String, Object> getQueryParam() {
    return queryParam;
  }

  public Payload getPayload() {
    return payload;
  }

  public RequestMethod getMethod() {
    return method;
  }

  public BodyType getBodyType() {
    return bodyType;
  }

  public Boolean isAuthAtHeader() {
    return authAtHeader;
  }
}
