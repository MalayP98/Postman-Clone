package com.clone.postmanc.exchange.request;

import java.util.Map;
import javax.el.MethodNotFoundException;
import com.clone.postmanc.exchange.response.Response;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequest;
import org.springframework.stereotype.Component;

@Component
public class RequestRunner {

  public Response run(ImmutableRequest request) throws UnirestException {
    Unirest.setTimeouts(0, 0);
    HttpRequest httpRequest = getRequestByMethod(request.getMethod(), request.getEndpoint());
    long startTime = System.currentTimeMillis();
    HttpResponse<JsonNode> response =
        httpRequest.headers(request.getHeaders()).queryString(request.getQueryParam()).asJson();
    long endTime = System.currentTimeMillis();
    return makeResponseObject(response, (int) (endTime - startTime));
  }

  @SuppressWarnings("unchecked")
  private Response makeResponseObject(HttpResponse<JsonNode> response, int timeDiff) {
    return new Response(response.getStatus(), (Map) response.getHeaders(),
        response.getBody().toString(), timeDiff);
  }

  private HttpRequest getRequestByMethod(RequestMethod method, String endpoint) {
    switch (method) {
      case GET:
        return Unirest.get(endpoint);
      case POST:
        return Unirest.post(endpoint);
      case DELETE:
        return Unirest.delete(endpoint);
      case PUT:
        return Unirest.put(endpoint);
      case PATCH:
        return Unirest.patch(endpoint);
      default:
        throw new MethodNotFoundException("No http method found to execute the request.");
    }
  }

}
