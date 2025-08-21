package com.example.demo.service;

import com.example.demo.model.Librarian;
import com.example.demo.repository.LibrarianRepository;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class LibrarianService {
  private final LibrarianRepository repo;
  public LibrarianService(LibrarianRepository repo){ this.repo = repo; }

  public List<Librarian> findAll(){ return repo.findAll(); }
  public Librarian create(Librarian l){ return repo.save(l); }
  public Librarian update(Long id, Librarian updated){
    return repo.findById(id).map(l -> {
      l.setName(updated.getName());
      l.setEmail(updated.getEmail());
      return repo.save(l);
    }).orElseThrow(() -> new RuntimeException("Librarian not found"));
  }
  public void delete(Long id){ repo.deleteById(id); }
}
