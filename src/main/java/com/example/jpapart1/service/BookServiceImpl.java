package com.example.jpapart1.service;

import com.example.jpapart1.entity.Book;
import com.example.jpapart1.exception.NotFoundException;
import com.example.jpapart1.repository.BookRepository;
import com.example.jpapart1.request.BookRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

  @Autowired
  private BookRepository bookRepository;

  /**
   * @return danh sách sách trong database
   */
  @Override
  public List<Book> getAllBook() {
    return bookRepository.findAll();
  }

  /**
   * @param id
   * @return sách tìm thấy theo id
   * @exception "Không tìm thấy sách theo id"
   */
  @Override
  public Book getBookByID(Long id) {
    Optional<Book> book = bookRepository.findById(id);
    if (book.isEmpty()) {
      throw new NotFoundException("Không tìm thấy sách với id = " + id, 400);
    }
    return book.get();
  }

  /**
   * @param name
   * @return danh sách book tìm thấy theo tên
   * @exception "Không tìm thấy sách theo tên"
   */
  @Override
  public List<Book> getBookByName(String name) {
    List<Book> bookList = bookRepository.findBookByName(name);
    if (bookList.isEmpty()){
      throw new NotFoundException("Không tìm thấy sách với tên = " + name, 500);
    }
    return bookList;
  }

  /**
   * @param bookRequest
   * @return book
   */
  @Override
  public Book insertBook(BookRequest bookRequest) {
    Book book = new Book();
    book.setBookName(bookRequest.getBookName());
    book.setYear(bookRequest.getYear());
    book.setLanguage(bookRequest.getLanguage());
    book.setNumberOfPages(bookRequest.getNumberOfPages());
    book.setOrigin(bookRequest.getOrigin());
    bookRepository.save(book);
    return book;
  }

  /**
   * @param bookRequest
   * @return book
   */
  @Override
  public Book updateBook(BookRequest bookRequest) {
    Optional<Book> bookSearch = bookRepository.findById(bookRequest.getId());
    if (bookSearch.isEmpty()){
      throw new NotFoundException("Không tìm thấy sách với id = " + bookRequest.getId(), 500);
    }
    Book book = bookSearch.get();
    book.setBookName(bookRequest.getBookName());
    book.setYear(bookRequest.getYear());
    book.setLanguage(bookRequest.getLanguage());
    book.setNumberOfPages(bookRequest.getNumberOfPages());
    book.setOrigin(bookRequest.getOrigin());
    bookRepository.save(book);
    return book;
  }

  /**
   * @param id
   */
  @Override
  public void deleteBook(Long id) {
    Optional<Book> book = bookRepository.findById(id);
    if (book.isEmpty()) {
      throw new NotFoundException("Không tìm thấy sách với id = " + id, 500);
    }
    bookRepository.delete(book.get());
  }
}
