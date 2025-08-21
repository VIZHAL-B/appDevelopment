package com.example.demo.repository;
import com.example.demo.model.Librarian;
import org.springframework.data.jpa.repository.JpaRepository;
public interface LibrarianRepository extends JpaRepository<Librarian, Long> {
}
