package com.clone.postmanc.exchange.request;

import java.util.Map;
import javax.persistence.AttributeConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

// VV stands for vice-versa.
public class MapToStringVVConverter implements AttributeConverter<Map<String, Object>, String> {

  @Override
  public String convertToDatabaseColumn(Map<String, Object> headerMap) {
    try {
      return new ObjectMapper().writeValueAsString(headerMap);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public Map<String, Object> convertToEntityAttribute(String headerJson) {
    try {
      return new ObjectMapper().readValue(headerJson, Map.class);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return null;
  }

}
