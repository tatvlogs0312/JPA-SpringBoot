package com.example.jpapart1.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class NotFoundException extends RuntimeException{
  private String error;
  private Integer httpStatus;

  public NotFoundException(String error,Integer httpStatus){
    super(error);
    this.httpStatus = httpStatus;
  }
}
