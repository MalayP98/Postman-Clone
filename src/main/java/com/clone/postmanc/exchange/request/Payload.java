package com.clone.postmanc.exchange.request;

import java.util.Map;
import javax.persistence.Convert;
import javax.persistence.Embeddable;

@Embeddable
public class Payload {

  String body;

  @Convert(converter = MapToStringVVConverter.class)
  Map<String, Object> field;

  @Convert(converter = MapToStringVVConverter.class)
  Map<String, String> files;

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public Map<String, Object> getField() {
    return field;
  }

  public void setField(Map<String, Object> field) {
    this.field = field;
  }

  public Map<String, String> getFiles() {
    return files;
  }

  public void setFiles(Map<String, String> files) {
    this.files = files;
  }
}
