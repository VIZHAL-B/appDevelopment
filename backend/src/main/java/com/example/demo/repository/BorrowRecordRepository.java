package com.example.demo.repository;

import com.example.demo.model.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {
  
  @Query("SELECT br FROM BorrowRecord br WHERE br.book.id = :bookId")
  List<BorrowRecord> findByBookId(@Param("bookId") Long bookId);
  
  @Query("SELECT br FROM BorrowRecord br WHERE br.borrower.id = :borrowerId")
  List<BorrowRecord> findByBorrowerId(@Param("borrowerId") Long borrowerId);
  
  @Query("SELECT br FROM BorrowRecord br WHERE br.book.id = :bookId AND br.returnDate IS NULL")
  List<BorrowRecord> findByBookIdAndReturnDateIsNull(@Param("bookId") Long bookId);
  
  @Query("SELECT br FROM BorrowRecord br WHERE br.borrower.id = :borrowerId AND br.returnDate IS NULL")
  List<BorrowRecord> findByBorrowerIdAndReturnDateIsNull(@Param("borrowerId") Long borrowerId);
  
  @Query("DELETE FROM BorrowRecord br WHERE br.book.id = :bookId")
  void deleteByBookId(@Param("bookId") Long bookId);
  
  @Query("DELETE FROM BorrowRecord br WHERE br.borrower.id = :borrowerId")
  void deleteByBorrowerId(@Param("borrowerId") Long borrowerId);
}
