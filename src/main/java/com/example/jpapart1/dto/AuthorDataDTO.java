package com.example.jpapart1.dto;

import com.example.jpapart1.entity.Book;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@RequiredArgsConstructor
@Getter
@Setter
public class AuthorDataDTO {
  private String name;
  private List<Book> books;
}
