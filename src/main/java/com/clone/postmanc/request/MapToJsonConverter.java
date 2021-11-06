package com.clone.postmanc.request;

import java.util.Map;
import javax.persistence.AttributeConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class MapToJsonConverter implements AttributeConverter<Map<String, Object>, String> {

  @Autowired
  private ObjectMapper objectMapper;

  @Override
  public String convertToDatabaseColumn(Map<String, Object> headerMap) {
    try {
      return objectMapper.writeValueAsString(headerMap);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public Map<String, Object> convertToEntityAttribute(String headerJson) {
    try {
      return objectMapper.readValue(headerJson, Map.class);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return null;
  }

}
