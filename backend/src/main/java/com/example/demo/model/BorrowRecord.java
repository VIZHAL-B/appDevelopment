package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class BorrowRecord {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(optional=false) @JoinColumn(name="borrower_id")
  private Borrower borrower;

  @ManyToOne(optional=false) @JoinColumn(name="book_id")
  private Book book;

  private LocalDate borrowDate;
  private LocalDate returnDate; // nullable until returned

  public BorrowRecord() {}

  public Long getId() { return id; }
  public Borrower getBorrower() { return borrower; }
  public void setBorrower(Borrower borrower) { this.borrower = borrower; }
  public Book getBook() { return book; }
  public void setBook(Book book) { this.book = book; }
  public LocalDate getBorrowDate() { return borrowDate; }
  public void setBorrowDate(LocalDate borrowDate) { this.borrowDate = borrowDate; }
  public LocalDate getReturnDate() { return returnDate; }
  public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }
}
