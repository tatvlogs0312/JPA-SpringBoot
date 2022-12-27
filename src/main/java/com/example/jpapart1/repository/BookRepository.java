package com.example.jpapart1.repository;

import com.example.jpapart1.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
  List<Book> findByBookNameIsLikeIgnoreCase(String bookName);

  @Query(value = "SELECT * FROM book b WHERE LOWER(b.book_name) LIKE LOWER(CONCAT('%',:name,'%'))",nativeQuery = true)
  List<Book> findBookByName(String name);
}