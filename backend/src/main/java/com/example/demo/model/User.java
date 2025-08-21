package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name="users")
public class User {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String email;
  private String contact;
  private String address;
  private String userType;

  public User() {}

  public Long getId() { return id; }
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  public String getEmail() { return email; }
  public void setEmail(String email) { this.email = email; }
  public String getContact() { return contact; }
  public void setContact(String contact) { this.contact = contact; }
  public String getAddress() { return address; }
  public void setAddress(String address) { this.address = address; }
  public String getUserType() { return userType; }
  public void setUserType(String userType) { this.userType = userType; }
}
