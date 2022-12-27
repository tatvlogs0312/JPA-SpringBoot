package com.example.jpapart1.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.*;

@Data
@Builder
@ToString
@AllArgsConstructor
public class HttpResponse {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy hh:mm:ss")
    private Date timeStamp;
    private int httpStatusCode; // 200, 201, 400, 500
    private HttpStatus httpStatus;
    private String message;
    private Set<String> errors;


    // Constructor never used. Can be (and should be) deleted
    public HttpResponse() {
        this.timeStamp = new Date();
        this.httpStatusCode = 200;
        this.httpStatus = HttpStatus.OK;
        this.errors = Collections.EMPTY_SET;
        this.message = "Success";
    }

    public HttpResponse(int httpStatusCode, HttpStatus httpStatus, String error, String message) {
        this.timeStamp = new Date();
        this.httpStatusCode = httpStatusCode;
        this.httpStatus = httpStatus;
        this.errors = Set.of(error);
        this.message = message;
    }
    public HttpResponse(int httpStatusCode, HttpStatus httpStatus, Set<String> errors, String message) {
        this.timeStamp = new Date();
        this.httpStatusCode = httpStatusCode;
        this.httpStatus = httpStatus;
        this.errors = errors;
        this.message = message;
    }
}
