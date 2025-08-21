package com.example.demo.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class Book {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;
  private String author;
  private boolean available = true;

  @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
private List<BorrowRecord> borrowRecords;


  public Book() {}
  public Book(String title, String author) { this.title = title; this.author = author; }

  public Long getId() { return id; }
  public String getTitle() { return title; }
  public void setTitle(String title) { this.title = title; }
  public String getAuthor() { return author; }
  public void setAuthor(String author) { this.author = author; }
  public boolean isAvailable() { return available; }
  public void setAvailable(boolean available) { this.available = available; }
}
