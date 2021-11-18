package com.clone.postmanc.exchange.request;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.el.MethodNotFoundException;
import com.clone.postmanc.exchange.response.Response;
import com.clone.postmanc.utils.AppConstants;
import com.clone.postmanc.utils.Helpers;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.BaseRequest;
import com.mashape.unirest.request.HttpRequest;
import com.mashape.unirest.request.HttpRequestWithBody;
import com.mashape.unirest.request.body.MultipartBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class RequestRunner {

  @Autowired
  Environment environment;

  private final String CONTENT_TYPE = "Content-Type";
  private final String AUTHORIZATION = "Authorization";

  public Response run(ImmutableRequest request, List<MultipartFile> files)
      throws UnirestException, IllegalStateException, IOException {
    Unirest.setTimeouts(0, 0);
    BaseRequest httpRequest = getHttpRequest(request, files);
    long startTime = System.currentTimeMillis();
    HttpResponse<String> response = httpRequest.asString();
    long endTime = System.currentTimeMillis();
    Helpers.cleanTempDirectory();
    return makeResponseObject(response, (int) (endTime - startTime));
  }

  @SuppressWarnings("unchecked")
  private Response makeResponseObject(HttpResponse<String> response, int timeDiff) {
    return new Response(response.getStatus(), (Map) response.getHeaders(),
        response.getBody().toString(), timeDiff);
  }

  private BaseRequest getHttpRequest(ImmutableRequest request, List<MultipartFile> files)
      throws IllegalStateException, IOException {
    HttpRequest httpRequest = getRequestByMethod(request.getMethod(), request.getEndpoint())
        .headers(request.getHeaders()).queryString(request.getQueryParam());
    if (request.getMethod().equals(RequestMethod.GET)) {
      return httpRequest;
    } else {
      setBody(httpRequest, request.getBodyType(), request.getPayload(), files);
    }
    System.out.println("\n\n\n" + request.getAuthentication());
    System.out.println("\n\n\n" + request.isAuthAtHeader());
    addAuthorization(request.getAuthentication(), request.isAuthAtHeader(), httpRequest);
    return httpRequest;
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

  private BaseRequest setBody(HttpRequest httpRequest, BodyType type, Payload payload,
      List<MultipartFile> files) throws IllegalStateException, IOException {
    HttpRequestWithBody httpRequestWithBody = ((HttpRequestWithBody) httpRequest);
    switch (type) {
      case JSON:
        return httpRequestWithBody.header(CONTENT_TYPE, "application/json").body(payload.getBody());
      case TEXT:
        return httpRequestWithBody.header(CONTENT_TYPE, "text/plain").body(payload.getBody());
      case XML:
        return httpRequestWithBody.header(CONTENT_TYPE, "application/xml").body(payload.getBody());
      case FORM_DATA:
        MultipartBody multipartRequest = httpRequestWithBody.fields(payload.getField());
        Map<String, String> fileInfo = payload.getFiles();
        for (MultipartFile file : files) {
          multipartRequest = multipartRequest.field(fileInfo.get(file.getOriginalFilename()),
              Helpers.getFile(file));
        }
        return multipartRequest;
      case BINARY:
        return ((HttpRequestWithBody) httpRequest).body(Helpers.getFile(files.get(0)));
      default:
        return null;
    }
  }

  private void addAuthorization(String authentication, boolean authAtHeader, HttpRequest request) {
    if (authAtHeader) {
      request.header(AUTHORIZATION, authentication);
    } else {
      String[] pair = authentication.split(AppConstants.COLON);
      request.queryString(pair[0], pair[1]);
    }
  }
}
