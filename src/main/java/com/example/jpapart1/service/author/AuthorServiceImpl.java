package com.example.jpapart1.service.author;

import com.example.jpapart1.dto.AuthorDataDTO;
import com.example.jpapart1.entity.Author;
import com.example.jpapart1.entity.Book;
import com.example.jpapart1.exception.NotFoundException;
import com.example.jpapart1.repository.AuthorRepository;
import com.example.jpapart1.repository.BookRepository;
import com.example.jpapart1.request.AuthorRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService{

  @Autowired
  private AuthorRepository authorRepository;

  @Autowired
  private BookRepository bookRepository;

  @Override
  public List<Author> getAllAuthor() {
    return authorRepository.findAll();
  }

  @Override
  public Author insertAuthor(AuthorRequest authorRequest) {
    Author author = new Author();
    author.setAuthorName(authorRequest.getName());
    authorRepository.save(author);
    return author;
  }

  @Override
  public Author updateAuthor(AuthorRequest authorRequest) {
    Optional<Author> authorOptional = authorRepository.findById(authorRequest.getId());
    if (authorOptional.isEmpty()){
      throw new NotFoundException("Không tìm thấy tác giả với id = " + authorRequest.getId(),404);
    }
    Author author = authorOptional.get();
    author.setAuthorName(authorRequest.getName());
    authorRepository.save(author);
    return author;
  }

  /**
   * @return authorDataDTOS
   */
  @Override
  public List<AuthorDataDTO> getAllAuthorData(){
    List<AuthorDataDTO> authorDataDTOS = new ArrayList<>();
    List<Author> authors = authorRepository.findAll();
    List<Book> books = bookRepository.findAll();

    //Duyệt từng tác giả để lấy sách
    authors.forEach(author -> {
      //Gọi hàm tạo authorDataDTO để lấy tất cả sách của author
      AuthorDataDTO authorDataDTO = getBookByAuthor(author,books);
      authorDataDTOS.add(authorDataDTO);
    });

    return authorDataDTOS;
  }

  /**
   * Service tạo authorDataDTO theo author và danh sách books truyền vào
   * @param author
   * @param books
   * @return authorDataDTO
   */
  public AuthorDataDTO getBookByAuthor(Author author, List<Book> books){
    AuthorDataDTO authorDataDTO = new AuthorDataDTO();
    authorDataDTO.setName(author.getAuthorName());

    //Filter books để lấy tất cả sách có id của author là id của author truyền vào
    List<Book> listBook = books.stream().filter(book -> book.getAuthor().getId().equals(author.getId())).collect(Collectors.toList());
    authorDataDTO.setBooks(listBook);
    return authorDataDTO;
  }
}
