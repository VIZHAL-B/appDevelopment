package com.example.demo.service;

import com.example.demo.model.Borrower;
import com.example.demo.model.BorrowRecord;
import com.example.demo.repository.BorrowerRepository;
import com.example.demo.repository.BorrowRecordRepository;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class BorrowerService {
  private final BorrowerRepository repo;
  private final BorrowRecordRepository borrowRecordRepo;
  
  public BorrowerService(BorrowerRepository repo, BorrowRecordRepository borrowRecordRepo){ 
    this.repo = repo;
    this.borrowRecordRepo = borrowRecordRepo;
  }

  public List<Borrower> findAll(){ return repo.findAll(); }
  public Borrower findOrThrow(Long id){
    return repo.findById(id).orElseThrow(() -> new RuntimeException("Borrower not found"));
  }
  public Borrower create(Borrower b){ return repo.save(b); }
  public Borrower update(Long id, Borrower updated){
    Borrower b = findOrThrow(id);
    b.setName(updated.getName());
    b.setContact(updated.getContact());
    return repo.save(b);
  }
  
  public void delete(Long id) {
    // Check if borrower has any active borrow records
    List<BorrowRecord> activeRecords = borrowRecordRepo.findByBorrowerIdAndReturnDateIsNull(id);
    if (!activeRecords.isEmpty()) {
      throw new RuntimeException("Cannot delete borrower with active borrow records");
    }
    
    // Check if borrower has any borrow records at all
    List<BorrowRecord> allRecords = borrowRecordRepo.findByBorrowerId(id);
    if (!allRecords.isEmpty()) {
      // Delete associated borrow records first
      borrowRecordRepo.deleteByBorrowerId(id);
    }
    
    repo.deleteById(id);
  }
  
  public boolean canDeleteBorrower(Long id) {
    return borrowRecordRepo.findByBorrowerIdAndReturnDateIsNull(id).isEmpty();
  }
}
