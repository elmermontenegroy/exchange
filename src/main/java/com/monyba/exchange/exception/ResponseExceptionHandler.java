package com.monyba.exchange.exception;

import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.sql.SQLException;
import java.time.Instant;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(Exception.class)
  public ProblemDetail handleAllExcepciones(Exception ex, WebRequest request){
    return generateProblemDetail(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), "Internal Server Error", request.getContextPath());
  }

  @ExceptionHandler(ModelNotFoundException.class)
  public ProblemDetail handleModelNotFoundException(ModelNotFoundException ex, WebRequest request){
    return ex.getBody();
  }

  @ExceptionHandler(SQLException.class)
  public ProblemDetail handleSQLException(SQLException ex, WebRequest request){
    return generateProblemDetail(HttpStatus.CONFLICT, ex.getMessage(), "Conflict", request.getContextPath());
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    String message = ex.getBindingResult().getAllErrors().stream().map(e -> e.getDefaultMessage().concat(", ")).collect(Collectors.joining());
    ProblemDetail problemDetail = generateProblemDetail(HttpStatus.BAD_REQUEST, message, "Bad Request", request.getContextPath());
    return new ResponseEntity<>(problemDetail, HttpStatus.BAD_REQUEST);
  }

  private ProblemDetail generateProblemDetail(HttpStatusCode httpStatusCode, String message, String title, String contextPath){
    ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(httpStatusCode, message);
    problemDetail.setTitle(title);
    problemDetail.setType(URI.create(contextPath));
    problemDetail.setProperty("timestamp", Instant.now());
    return problemDetail;
  }

}
