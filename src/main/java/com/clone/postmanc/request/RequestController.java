package com.clone.postmanc.request;

import com.clone.postmanc.utils.AppConstants;
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
  public IncomingRequest send(@RequestBody IncomingRequest incomingRequest)
      throws JSONException, NotFoundException {
    requestService.runRequest(incomingRequest);
    return incomingRequest;
  }

}
