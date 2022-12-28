package com.example.jpapart1.exception;

import com.example.jpapart1.dto.HttpResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
public class ExceptionHandling {
  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<HttpResponse> notFoundException(NotFoundException exception) {
    var errorCode = HttpStatus.resolve(exception.getHttpStatus());
    if (errorCode == null) {
      errorCode = INTERNAL_SERVER_ERROR;
    }
    return createHttpResponse(errorCode, exception.getMessage());
  }

  private ResponseEntity<HttpResponse> createHttpResponse(HttpStatus httpStatus, String message) {
    return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus,
        httpStatus.getReasonPhrase().toUpperCase(), message), httpStatus);
  }

}
