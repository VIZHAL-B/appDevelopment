package com.example.demo.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class Borrower {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String contact;

  @OneToMany(mappedBy = "borrower", cascade = CascadeType.ALL, orphanRemoval = true)
private List<BorrowRecord> borrowRecords;

  public Borrower() {}
  public Borrower(String name, String contact) { this.name = name; this.contact = contact; }

  public Long getId() { return id; }
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  public String getContact() { return contact; }
  public void setContact(String contact) { this.contact = contact; }
}
