package com.clone.postmanc.request;

import java.time.LocalTime;
import java.util.List;
import com.clone.postmanc.utils.Helpers;
import com.mashape.unirest.http.exceptions.UnirestException;
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

  @Autowired
  private RequestRunner requestRunner;

  public void runRequest(IncomingRequest incomingRequest)
      throws JSONException, NotFoundException, UnirestException {
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
    send(new ImmutableRequest(request));
  }

  public void send(ImmutableRequest request) throws UnirestException {
    requestRunner.run(request);
  }

  public void saveIfAbsent(Request request) throws Exception {
    List<Request> requests = requestRepositiory.exists(request);
    if (requests.size() == 0) {
      LOG.info("Saving request with id {}.", request.getId());
      requestRepositiory.save(request);
    } else if (requests.size() == 1) {
      request.setId(requests.get(0).getId());
      requestRepositiory.save(request);
      LOG.info("Updating request with id {}.", request.getId());
    } else {
      throw new Exception("More that one request found with same endpoint and user id.");
    }
  }
}
