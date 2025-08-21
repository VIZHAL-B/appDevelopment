package com.example.demo.controller;

import com.example.demo.model.Borrower;
import com.example.demo.service.BorrowerService;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController @RequestMapping("/api/borrowers")
public class BorrowerController {
  private final BorrowerService service;
  public BorrowerController(BorrowerService s){ this.service = s; }

  @GetMapping public List<Borrower> all(){ return service.findAll(); }
  @PostMapping public Borrower create(@RequestBody Borrower b){ return service.create(b); }
  @PutMapping("/{id}") public Borrower update(@PathVariable Long id, @RequestBody Borrower b){ return service.update(id, b); }
  @DeleteMapping("/{id}") public void delete(@PathVariable Long id){ service.delete(id); }
}
