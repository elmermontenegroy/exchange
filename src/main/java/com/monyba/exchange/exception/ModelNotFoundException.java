package com.monyba.exchange.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

import java.net.URI;
import java.time.Instant;

public class ModelNotFoundException extends ErrorResponseException {

  public ModelNotFoundException(String message){
    super(HttpStatus.NOT_FOUND, getDetails(message), null);
  }

  private static ProblemDetail getDetails(String message){
    ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, message);
    problemDetail.setTitle("Model Not Found");
    problemDetail.setProperty("timestamp", Instant.now());
    problemDetail.setType(URI.create(""));
    return problemDetail;
  }

}
