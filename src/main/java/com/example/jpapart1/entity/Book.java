package com.example.jpapart1.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "book")
@Table(name = "book")
public class Book {

  @Id
  @GeneratedValue(generator = "book_seq",strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(name = "book_seq",sequenceName = "book_seq_id", allocationSize = 1)
  private Long id;

  @Column(name = "book_name")
  private String bookName;

  @Column(name = "year")
  private int year;

  @Column(name = "language")
  private String language;

  @Column(name = "number_of_pages")
  private int numberOfPages;

  @Column(name = "origin")
  private String origin;

  //@ManyToOne => 1 sách chỉ có 1 tác giả
  @ManyToOne
  @JoinColumn(name = "author_id")
  @JsonIgnore
  private Author author;

}
