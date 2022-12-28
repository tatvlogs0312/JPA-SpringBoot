package com.example.jpapart1.controller;

import com.example.jpapart1.dto.BookDataDTO;
import com.example.jpapart1.entity.Book;
import com.example.jpapart1.request.BookRequest;
import com.example.jpapart1.service.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

  @Autowired
  private BookService bookService;


  /**
   * api lấy tất cả sách sử dụng get
   * @return danh sách tất cả sách
   */
  @GetMapping
  public ResponseEntity<List<Book>> getAllBook(){
    return new ResponseEntity<>(bookService.getAllBook(), HttpStatus.OK);
  }

  /**
   * api tìm sách theo id dùng get
   * @param id
   * @return book tìm thấy
   */
  @GetMapping("/{id}")
  public ResponseEntity<Book> getBookByID(@PathVariable Long id){
    return new ResponseEntity<>(bookService.getBookByID(id), HttpStatus.OK);
  }

  /**
   * api tìm book theo tên dùng get + @RequestParam
   * @param name
   * @return danh sách book theo tên
   */
  @GetMapping("/search-by-name")
  public ResponseEntity<List<Book>> getBookByNameV1(@RequestParam String name){
    return new ResponseEntity<>(bookService.getBookByName(name),HttpStatus.OK);
  }

  /**
   * api tìm book theo tên dùng post + @RequestBody
   * @param bookRequest
   * @return danh sách book theo tên
   */
  @PostMapping("/search-by-name")
  public ResponseEntity<List<Book>> getBookByNameV2(@RequestBody BookRequest bookRequest){
    return new ResponseEntity<>(bookService.getBookByName(bookRequest.getBookName()),HttpStatus.OK);
  }

  /**
   * api thêm book
   * @param bookRequest
   * @return book đã được thêm
   */
  @PostMapping("/add-book")
  public ResponseEntity<Book> addBook(@RequestBody BookRequest bookRequest){
    return new ResponseEntity<>(bookService.insertBook(bookRequest),HttpStatus.OK);
  }

  /**
   * api sửa book
   * @param bookRequest
   * @return book đã sửa
   */
  @PutMapping("/update-book")
  public ResponseEntity<Book> updateBook(@RequestBody BookRequest bookRequest){
    return new ResponseEntity<>(bookService.updateBook(bookRequest),HttpStatus.OK);
  }

  /**
   * api xóa book
   * @param id
   * @return message
   */
  @DeleteMapping("/delete-book/{id}")
  public ResponseEntity<String> deleteBook(@PathVariable Long id){
    bookService.deleteBook(id);
    return new ResponseEntity<>("Xóa thành công",HttpStatus.OK);
  }

  /**
   * @return danh sách book kèm thông tin tác giả
   */
  @GetMapping("/get-book-data")
  public ResponseEntity<List<BookDataDTO>> getBookData(){
    return new ResponseEntity<>(bookService.getBookData(),HttpStatus.OK);
  }
}
