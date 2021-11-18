package com.clone.postmanc.exchange.request;

import java.io.IOException;
import java.util.List;
import com.clone.postmanc.exchange.response.Response;
import com.clone.postmanc.utils.AppConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javassist.NotFoundException;

@RestController
@RequestMapping(AppConstants.API_URL + AppConstants.REQUEST)
public class RequestController {

  @Autowired
  private RequestService requestService;

  /**
   * All the incomming request will considered multipart wether the have a file body or not.
   * The @param body will be converted into IncomingRequest class which will contain all the data to
   * hit the API.
   * 
   * @param body,  will contain the string part which can be json, text, xml (anything in string
   *               format).
   * @param files, will contain all the files being send.
   */
  @PostMapping(value = AppConstants.SEND, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public Response send(@RequestPart String body, @RequestPart List<MultipartFile> files)
      throws JsonMappingException, IllegalStateException, JsonProcessingException, JSONException,
      NotFoundException, UnirestException, IOException {
    return requestService.runRequest(new ObjectMapper().readValue(body, IncomingRequest.class),
        files);
  }
}
