package com.example.jpapart1.service.author;

import com.example.jpapart1.dto.AuthorDataDTO;
import com.example.jpapart1.entity.Author;
import com.example.jpapart1.request.AuthorRequest;

import java.util.List;

public interface AuthorService {
  List<Author> getAllAuthor();
  Author insertAuthor(AuthorRequest authorRequest);
  Author updateAuthor(AuthorRequest authorRequest);
  List<AuthorDataDTO> getAllAuthorData();
}
