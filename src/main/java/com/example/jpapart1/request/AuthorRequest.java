package com.example.jpapart1.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Setter
@Getter
public class AuthorRequest {
  private Long id;
  private String name;
}
