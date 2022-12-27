package com.example.jpapart1.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class BookRequest {
  private Long id;
  private String bookName;
  private int year;
  private String language;
  private int numberOfPages;
  private String origin;
}
