package com.clone.postmanc.exchange.response;

import java.util.List;
import com.clone.postmanc.exchange.exception.DuplicateElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResponseService {

  @Autowired
  private ResponseRepository responseRepository;

  public Response saveResponse(Response response, long requestId, boolean keepDuplicates)
      throws Exception {
    response.setRequestId(requestId);
    if (keepDuplicates) {
      return responseRepository.save(response);
    } else {
      List<Response> responses = responseRepository.findByRequestId(requestId);
      if (responses.size() == 0) {
        return responseRepository.save(response);
      } else if (responses.size() == 1) {
        response.setId(responses.get(0).getId());
        responseRepository.save(response);
      }
      throw new DuplicateElementException("Multiple response cannot exist for same request.");
    }
  }
}
