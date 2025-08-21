package com.example.demo.controller;

import com.example.demo.model.Librarian;
import com.example.demo.service.LibrarianService;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController @RequestMapping("/api/librarians")
public class LibrarianController {
  private final LibrarianService service;
  public LibrarianController(LibrarianService s){ this.service = s; }

  @GetMapping public List<Librarian> all(){ return service.findAll(); }
  @PostMapping public Librarian create(@RequestBody Librarian l){ return service.create(l); }
  @PutMapping("/{id}") public Librarian update(@PathVariable Long id, @RequestBody Librarian l){ return service.update(id,l); }
  @DeleteMapping("/{id}") public void delete(@PathVariable Long id){ service.delete(id); }
}
