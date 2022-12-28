package com.example.jpapart1.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "author")
@Table(name = "author")
public class Author {

  @Id
  @GeneratedValue(generator = "author_seq", strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(name = "author_seq", sequenceName = "author_seq_id", allocationSize = 1)
  private Long id;

  @Column(name = "author_name")
  private String authorName;

  //@OneToMany => 1 tác giả có nhiều sách
  @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
  @JsonIgnore
  private List<Book> books;

}
