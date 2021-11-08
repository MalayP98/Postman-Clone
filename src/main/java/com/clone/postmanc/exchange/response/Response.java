package com.clone.postmanc.exchange.response;

import java.util.Map;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.clone.postmanc.exchange.request.MapToJsonConverter;

@Entity
public class Response {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  private int statusCode;

  @Convert(converter = MapToJsonConverter.class)
  @Column(columnDefinition = "text")
  private Map<String, Object> headers;

  @Column(columnDefinition = "text")
  private String body;

  private int timeDiff;

  private long requestId;

  public Response() {

  }

  public Response(int statusCode, Map<String, Object> headers, String body, int timeDiff) {
    this.statusCode = statusCode;
    this.headers = headers;
    this.body = body;
    this.timeDiff = timeDiff;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Map<String, Object> getHeaders() {
    return headers;
  }

  public void setHeaders(Map<String, Object> headers) {
    this.headers = headers;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public int getTimeDiff() {
    return timeDiff;
  }

  public void setTimeDiff(int timeDiff) {
    this.timeDiff = timeDiff;
  }

  public int getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(int statusCode) {
    this.statusCode = statusCode;
  }

  public long getRequestId() {
    return requestId;
  }

  public void setRequestId(long requestId) {
    this.requestId = requestId;
  }
}
