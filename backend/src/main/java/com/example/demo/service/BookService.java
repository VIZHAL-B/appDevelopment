package com.example.demo.service;

import com.example.demo.model.Book;
import com.example.demo.model.BorrowRecord;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.BorrowRecordRepository;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class BookService {
  private final BookRepository repo;
  private final BorrowRecordRepository borrowRecordRepo;
  
  public BookService(BookRepository repo, BorrowRecordRepository borrowRecordRepo){ 
    this.repo = repo;
    this.borrowRecordRepo = borrowRecordRepo;
  }

  public List<Book> findAll(){ return repo.findAll(); }
  public Optional<Book> findById(Long id){ return repo.findById(id); }
  public Book create(Book b){ return repo.save(b); }
  public Book update(Long id, Book updated){
    return repo.findById(id).map(b -> {
      b.setTitle(updated.getTitle());
      b.setAuthor(updated.getAuthor());
      b.setAvailable(updated.isAvailable());
      return repo.save(b);
    }).orElseThrow(() -> new RuntimeException("Book not found"));
  }
  
  public void delete(Long id) {
    // Check if book has any active borrow records
    List<BorrowRecord> activeRecords = borrowRecordRepo.findByBookIdAndReturnDateIsNull(id);
    if (!activeRecords.isEmpty()) {
      throw new RuntimeException("Cannot delete book with active borrow records");
    }
    
    // Check if book has any borrow records at all
    List<BorrowRecord> allRecords = borrowRecordRepo.findByBookId(id);
    if (!allRecords.isEmpty()) {
      // Delete associated borrow records first
      borrowRecordRepo.deleteByBookId(id);
    }
    
    repo.deleteById(id);
  }
  
  public boolean canDeleteBook(Long id) {
    return borrowRecordRepo.findByBookIdAndReturnDateIsNull(id).isEmpty();
  }
}
