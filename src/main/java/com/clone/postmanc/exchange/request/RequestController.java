package com.clone.postmanc.exchange.request;

import com.clone.postmanc.exchange.response.Response;
import com.clone.postmanc.utils.AppConstants;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javassist.NotFoundException;

@RestController
@RequestMapping(AppConstants.API_URL + AppConstants.REQUEST)
public class RequestController {

  @Autowired
  private RequestService requestService;

  @PostMapping(value = AppConstants.SEND)
  public Response send(@RequestBody IncomingRequest incomingRequest)
      throws JSONException, NotFoundException, UnirestException {
    return requestService.runRequest(incomingRequest);
  }

}
