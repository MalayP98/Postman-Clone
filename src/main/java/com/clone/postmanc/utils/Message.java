package com.clone.postmanc.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

public class Message {

  private String time;

  private Date date;

  private String message;

  public Message(String message) {
    DateFormat formatter = new SimpleDateFormat(AppConstants.DATE_FORMAT);
    try {
      this.date = formatter.parse(formatter.format(new Date()));
    } catch (ParseException e) {
      e.printStackTrace();
    }
    this.time = LocalTime.now().toString();
    this.message = message;
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
}
