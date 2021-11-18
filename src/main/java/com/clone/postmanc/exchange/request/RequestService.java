package com.clone.postmanc.exchange.request;

import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;
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

  /**
   * Main thread is reponsible for running the API, the child thread is responsible for saving
   * request and response. Both the tasks are performed simulteneoulsy so that saving the data has
   * no effect on time that it takes to run the request.
   * 
   * @throws IOException
   * @throws IllegalStateException
   */
  public Response runRequest(IncomingRequest incomingRequest, List<MultipartFile> files)
      throws JSONException, NotFoundException, UnirestException, IllegalStateException,
      IOException {
    incomingRequest.setUserId(Helpers.getPrincipal().getId());
    Request request = new Request(incomingRequest);
    Response response = send(new ImmutableRequest(incomingRequest), files);
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

  private Response send(ImmutableRequest request, List<MultipartFile> files)
      throws UnirestException, IllegalStateException, IOException {
    return requestRunner.run(request, files);
  }

  /**
   * @param keepDuplicate, If set to true then Request is saved even if it is already present in the
   *                       database. If set to false then it is checked that wether the object is
   *                       already present in the database or not and is saved only if the object is
   *                       not present. If single instance is present of the object then that object
   *                       is updated. If more that one instance is present then
   *                       DuplicateElementException is thrown.
   */
  private Request saveRequest(Request request, boolean keepDuplicate) throws Exception {
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
