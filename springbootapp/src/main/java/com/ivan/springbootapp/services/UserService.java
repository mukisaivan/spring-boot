package com.ivan.springbootapp.services;

// import org.hibernate.mapping.List;

// import org.hibernate.mapping.Map;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.ivan.springbootapp.repositories.UserRepositoryClass;

public class UserService {

  private final UserRepositoryClass userRepository;

  @Autowired
  public UserService(UserRepositoryClass userRepository) {
    this.userRepository = userRepository;
  }

  public void addUser() {
    userRepository.insertUser("john_doe", "john@example.com");
  }

  public List<Map<String, Object>> listUsers() {
    return userRepository.getAllUsers();
  }
}
