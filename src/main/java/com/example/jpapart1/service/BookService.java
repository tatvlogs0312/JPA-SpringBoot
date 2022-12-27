package com.example.jpapart1.service;

import com.example.jpapart1.entity.Book;
import com.example.jpapart1.request.BookRequest;

import java.util.List;

public interface BookService {
  List<Book> getAllBook();
  Book getBookByID(Long id);
  List<Book> getBookByName(String name);
  Book insertBook(BookRequest bookRequest);
  Book updateBook(BookRequest bookRequest);
  void deleteBook(Long id);
}
