package com.example.jpapart1.service.book;

import com.example.jpapart1.dto.BookDataDTO;
import com.example.jpapart1.entity.Author;
import com.example.jpapart1.entity.Book;
import com.example.jpapart1.exception.NotFoundException;
import com.example.jpapart1.repository.AuthorRepository;
import com.example.jpapart1.repository.BookRepository;
import com.example.jpapart1.request.BookRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

  @Autowired
  private BookRepository bookRepository;

  @Autowired
  private AuthorRepository authorRepository;

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
      throw new NotFoundException("Không tìm thấy sách với id = " + id, 404);
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
      throw new NotFoundException("Không tìm thấy sách với tên = " + name, 404);
    }
    return bookList;
  }

  /**
   * @param bookRequest
   * @return book
   */
  @Override
  public Book insertBook(BookRequest bookRequest) {
    Optional<Author> authorOptional = authorRepository.findById(bookRequest.getIdAuthor());
    if(authorOptional.isEmpty()){
      throw new NotFoundException("Không tìm thấy tác giả",404);
    }
    Book book = new Book();
    book.setBookName(bookRequest.getBookName());
    book.setYear(bookRequest.getYear());
    book.setLanguage(bookRequest.getLanguage());
    book.setNumberOfPages(bookRequest.getNumberOfPages());
    book.setOrigin(bookRequest.getOrigin());
    book.setAuthor(authorOptional.get());
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
      throw new NotFoundException("Không tìm thấy sách với id = " + bookRequest.getId(), 404);
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
      throw new NotFoundException("Không tìm thấy sách với id = " + id, 404);
    }
    bookRepository.delete(book.get());
  }

  /**
   * @return danh sách book kèm tên tác giả
   */
  @Override
  public List<BookDataDTO> getBookData(){
    List<Author> authors = authorRepository.findAll();
    List<Book> books = bookRepository.findAll();
    List<BookDataDTO> bookDataDTOS = books.stream().map(book -> createBookData(book,authors)).collect(Collectors.toList());
    return bookDataDTOS;
  }

  public BookDataDTO createBookData(Book book, List<Author> authors){
    BookDataDTO bookDataDTO = new BookDataDTO();
    bookDataDTO.setBookName(book.getBookName());
    bookDataDTO.setYear(book.getYear());
    bookDataDTO.setOrigin(book.getOrigin());
    bookDataDTO.setLanguage(book.getLanguage());
    bookDataDTO.setNumberOfPage(book.getNumberOfPages());

    //Tìm kiểm tác giả của sách
    Optional<Author> authorOptional = authors.stream().filter(author -> book.getAuthor().getId().equals(author.getId())).findFirst();
    if (authorOptional.isPresent()){
      bookDataDTO.setAuthorName(authorOptional.get().getAuthorName());
    }

    return bookDataDTO;
  }
}
