package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController @RequestMapping("/api/users")
public class UserController {
  private final UserService service;
  public UserController(UserService s){ this.service = s; }

  @GetMapping public List<User> all(){ return service.findAll(); }
  @PostMapping public User create(@RequestBody User u){ return service.create(u); }
  @PutMapping("/{id}") public User update(@PathVariable Long id, @RequestBody User u){ return service.update(id, u); }
  @DeleteMapping("/{id}") public void delete(@PathVariable Long id){ service.delete(id); }
}
