package com.clone.postmanc.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.HttpStatus;

public class Message {

  private String time;

  private Date date;

  private String message;

  // Can be null.
  @JsonIgnore
  private HttpStatus status;

  private Message() {
    DateFormat formatter = new SimpleDateFormat(AppConstants.DATE_FORMAT);
    try {
      this.date = formatter.parse(formatter.format(new Date()));
    } catch (ParseException e) {
      e.printStackTrace();
    }
    this.time = LocalTime.now().toString();
  }

  public Message(String message) {
    this(message, null);
  }

  public Message(String message, HttpStatus status) {
    this();
    this.message = message;
    this.status = status;
  }

  public String getTime() {
    return time;
  }

  public Date getDate() {
    return date;
  }

  public String getMessage() {
    return message;
  }

  public HttpStatus getStatus() {
    return status;
  }
}
