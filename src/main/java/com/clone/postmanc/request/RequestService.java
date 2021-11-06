package com.clone.postmanc.request;

import java.time.LocalTime;
import java.util.List;
import com.clone.postmanc.utils.Helpers;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javassist.NotFoundException;

@Service
public class RequestService {

  Logger LOG = LoggerFactory.getLogger(getClass());

  @Autowired
  private RequestRepositiory requestRepositiory;

  public void runRequest(IncomingRequest incomingRequest) throws JSONException, NotFoundException {
    incomingRequest.setUserId(Helpers.getPrincipal().getId());
    Request request = new Request(incomingRequest);
    Thread ct = new Thread() {
      public void run() {
        try {
          saveIfAbsent(request);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    };
    ct.start();
    send();
  }

  public void send() {
    System.out.println("\n\n sending request at " + LocalTime.now());
  }

  public void saveIfAbsent(Request request) throws Exception {
    List<Request> requests = requestRepositiory.exists(request);
    if (requests.size() == 0) {
      requestRepositiory.save(request);
    } else if (requests.size() == 1) {
      request.setId(requests.get(0).getId());
      requestRepositiory.save(request);
    } else {
      throw new Exception("More that one request found with same endpoint and user id.");
    }
  }
}
