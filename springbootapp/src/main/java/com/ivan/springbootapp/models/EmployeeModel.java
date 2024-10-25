package com.ivan.springbootapp.models;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "employees")
@Component
public class EmployeeModel {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  private String firstName;

  private String lastName;

  private LocalDate dateOfBirth;

  // Hibernate expects entities to have a no-arg constructor,
  // though it does not necessarily have to be public.
  public EmployeeModel() {
  }

  public EmployeeModel(String firstName, String lastName, LocalDate dateOfBirth) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.dateOfBirth = dateOfBirth;
  }

  public Integer getId() {
    return this.id;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public LocalDate getDateOfBirth() {
    return this.dateOfBirth;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setDateOfBirth(LocalDate dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }
}