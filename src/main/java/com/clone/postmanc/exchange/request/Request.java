package com.clone.postmanc.exchange.request;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
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

  @CreationTimestamp
  private LocalDateTime creation;

  @UpdateTimestamp
  private LocalDateTime update;

  public Request() {

  }

  public Request(IncomingRequest incomingRequest) throws JSONException, NotFoundException {
    super(incomingRequest);
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

  public LocalDateTime getCreation() {
    return creation;
  }

  public void setCreation(LocalDateTime creation) {
    this.creation = creation;
  }

  public LocalDateTime getUpdate() {
    return update;
  }

  public void setUpdate(LocalDateTime update) {
    this.update = update;
  }
}
