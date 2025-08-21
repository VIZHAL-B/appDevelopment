package com.example.demo.service;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.*;

@Service
public class BorrowRecordService {
  private final BorrowRecordRepository recordRepo;
  private final BookRepository bookRepo;
  private final BorrowerRepository borrowerRepo;

  public BorrowRecordService(BorrowRecordRepository r, BookRepository b, BorrowerRepository br){
    this.recordRepo = r; this.bookRepo = b; this.borrowerRepo = br;
  }

  public List<BorrowRecord> findAll(){ return recordRepo.findAll(); }
  public BorrowRecord findOrThrow(Long id){
    return recordRepo.findById(id).orElseThrow(() -> new RuntimeException("BorrowRecord not found"));
  }

  public BorrowRecord create(Long borrowerId, Long bookId){
    Borrower borrower = borrowerRepo.findById(borrowerId)
      .orElseThrow(() -> new RuntimeException("Borrower not found"));
    Book book = bookRepo.findById(bookId)
      .orElseThrow(() -> new RuntimeException("Book not found"));
    if(!book.isAvailable()) throw new RuntimeException("Book not available");

    book.setAvailable(false);
    bookRepo.save(book);

    BorrowRecord rec = new BorrowRecord();
    rec.setBorrower(borrower);
    rec.setBook(book);
    rec.setBorrowDate(LocalDate.now());
    return recordRepo.save(rec);
  }

  public BorrowRecord returnBook(Long recordId){
    BorrowRecord rec = findOrThrow(recordId);
    if(rec.getReturnDate() == null){
      rec.setReturnDate(LocalDate.now());
      Book b = rec.getBook();
      b.setAvailable(true);
      bookRepo.save(b);
    }
    return recordRepo.save(rec);
  }

  public void delete(Long id){
    BorrowRecord rec = findOrThrow(id);
    Book b = rec.getBook();
    b.setAvailable(true);
    bookRepo.save(b);
    recordRepo.deleteById(id);
  }
}
