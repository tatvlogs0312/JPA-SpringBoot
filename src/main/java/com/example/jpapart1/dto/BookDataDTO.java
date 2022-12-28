package com.example.jpapart1.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class BookDataDTO {
  private String bookName;
  private String authorName;
  private String language;
  private Integer numberOfPage;
  private String origin;
  private Integer year;
}
