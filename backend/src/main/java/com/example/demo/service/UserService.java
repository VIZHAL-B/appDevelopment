package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class UserService {
  private final UserRepository repo;
  public UserService(UserRepository repo){ this.repo = repo; }

  public List<User> findAll(){ return repo.findAll(); }
  public User findOrThrow(Long id){
    return repo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
  }
  public User create(User u){ return repo.save(u); }
  public User update(Long id, User updated){
    User u = findOrThrow(id);
    u.setName(updated.getName());
    u.setEmail(updated.getEmail());
    u.setContact(updated.getContact());
    u.setAddress(updated.getAddress());
    u.setUserType(updated.getUserType());
    return repo.save(u);
  }
  public void delete(Long id){ repo.deleteById(id); }
}
