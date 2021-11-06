package com.clone.postmanc.request;

import javax.el.MethodNotFoundException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequest;
import org.springframework.stereotype.Component;

@Component
public class RequestRunner {

  public void run(ImmutableRequest request) throws UnirestException {
    HttpRequest httpRequest = getRequestByMethod(request.getMethod(), request.getEndpoint());
    HttpResponse<JsonNode> response =
        httpRequest.headers(request.getHeaders()).queryString(request.getQueryParam()).asJson();
    System.out.println(response.getBody());
    System.out.println(response.getHeaders());
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
