package com.ivan.springbootapp.repositories;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryClass {

  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public UserRepositoryClass(DataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
  }

  // Query: Retrieve all users
  public List<Map<String, Object>> getAllUsers() {
    String sql = "SELECT * FROM users";
    return jdbcTemplate.queryForList(sql);
  }

  // Query: Insert a new user
  public int insertUser(String username, String email) {
    String sql = "INSERT INTO users (username, email) VALUES (?, ?)";
    return jdbcTemplate.update(sql, username, email);
  }

  // Query: Update an existing user's email
  public int updateUserEmail(int userId, String newEmail) {
    String sql = "UPDATE users SET email = ? WHERE id = ?";
    return jdbcTemplate.update(sql, newEmail, userId);
  }

  // Query: Delete a user by ID
  public int deleteUser(int userId) {
    String sql = "DELETE FROM users WHERE id = ?";
    return jdbcTemplate.update(sql, userId);
  }

  // Query: Find a user by username
  public Map<String, Object> findUserByUsername(String username) {
    String sql = "SELECT * FROM users WHERE username = ?";
    return jdbcTemplate.queryForMap(sql, username);
  }
}
