package com.example.jpapart1.controller;

import com.example.jpapart1.dto.AuthorDataDTO;
import com.example.jpapart1.entity.Author;
import com.example.jpapart1.request.AuthorRequest;
import com.example.jpapart1.service.author.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

  @Autowired
  private AuthorService authorService;

  /**
   * @return danh sách tác giả không có thông tin sách
   */
  @GetMapping
  public ResponseEntity<List<Author>> getAllAuthor() {
    return new ResponseEntity<>(authorService.getAllAuthor(), HttpStatus.OK);
  }

  /**
   * @param authorRequest
   * @return tác giả vừa được thêm
   */
  @PostMapping("/add-author")
  public ResponseEntity<Author> addAuthor(@RequestBody AuthorRequest authorRequest) {
    return new ResponseEntity<>(authorService.insertAuthor(authorRequest), HttpStatus.OK);
  }

  /**
   * @param authorRequest
   * @return tác giả vừa được sửa
   */
  @PutMapping("/update-author")
  public ResponseEntity<Author> updateAuthor(@RequestBody AuthorRequest authorRequest) {
    return new ResponseEntity<>(authorService.updateAuthor(authorRequest), HttpStatus.OK);
  }

  /**
   * @return danh sách tác giả + sách của từng tác giả
   */
  @GetMapping("/get-author-data")
  public ResponseEntity<List<AuthorDataDTO>> getAllAuthorData() {
    return new ResponseEntity<>(authorService.getAllAuthorData(), HttpStatus.OK);
  }

}
