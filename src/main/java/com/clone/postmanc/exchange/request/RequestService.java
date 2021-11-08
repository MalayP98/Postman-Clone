package com.clone.postmanc.exchange.request;

import java.util.List;
import com.clone.postmanc.exchange.exception.DuplicateElementException;
import com.clone.postmanc.exchange.response.Response;
import com.clone.postmanc.exchange.response.ResponseService;
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

  @Autowired
  private ResponseService responseService;

  public Response runRequest(IncomingRequest incomingRequest)
      throws JSONException, NotFoundException, UnirestException {
    incomingRequest.setUserId(Helpers.getPrincipal().getId());
    Request request = new Request(incomingRequest);
    Response response = send(new ImmutableRequest(request));
    Thread ct = new Thread() {
      public void run() {
        try {
          saveRequest(request, false);
          responseService.saveResponse(response, request.getId(), false);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    };
    ct.start();
    return response;
  }

  public Response send(ImmutableRequest request) throws UnirestException {
    return requestRunner.run(request);
  }

  public Request saveRequest(Request request, boolean keepDuplicate) throws Exception {
    List<Request> requests =
        requestRepositiory.findByEndpointAndUserId(request.getEndpoint(), request.getUserId());
    if (keepDuplicate) {
      return requestRepositiory.save(request);
    } else {
      if (requests.size() == 0) {
        LOG.info("Saving request with id {}.", request.getId());
        return requestRepositiory.save(request);
      } else if (requests.size() == 1) {
        request.setId(requests.get(0).getId());
        LOG.info("Updating request with id {}.", request.getId());
        return requestRepositiory.save(request);
      }
      throw new DuplicateElementException(
          "More that one request found with same endpoint and user id.");
    }
  }
}
