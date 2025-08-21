package com.example.demo.controller;

import com.example.demo.model.BorrowRecord;
import com.example.demo.service.BorrowRecordService;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController @RequestMapping("/api/borrow-records")
public class BorrowRecordController {
  private final BorrowRecordService service;
  public BorrowRecordController(BorrowRecordService s){ this.service = s; }

  @GetMapping public List<BorrowRecord> all(){ return service.findAll(); }

  // Create a record by borrowerId and bookId
  @PostMapping
  public BorrowRecord create(@RequestParam Long borrowerId, @RequestParam Long bookId){
    return service.create(borrowerId, bookId);
  }

  // Return a book
  @PostMapping("/{id}/return")
  public BorrowRecord returnBook(@PathVariable Long id){ return service.returnBook(id); }

  @DeleteMapping("/{id}") public void delete(@PathVariable Long id){ service.delete(id); }
}
