package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.service.BookServiceFixed;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController @RequestMapping("/api/books")
public class BookController {
  private final BookServiceFixed service;
  public BookController(BookServiceFixed s){ this.service = s; }

  @GetMapping public List<Book> all(){ return service.findAll(); }
  @GetMapping("/{id}") public Optional<Book> one(@PathVariable Long id){ return service.findById(id); }
  @PostMapping public Book create(@RequestBody Book b){ return service.create(b); }
  @PutMapping("/{id}") public Book update(@PathVariable Long id, @RequestBody Book b){ return service.update(id, b); }
  @DeleteMapping("/{id}") public void delete(@PathVariable Long id){ service.delete(id); }
}
