package com.ivan.springbootapp.controllers;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ivan.springbootapp.models.EmployeeModel;
import com.ivan.springbootapp.repositories.EmployeeRepositoryClass;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeeController {

  private final EmployeeRepositoryClass employeeRepository;

  public EmployeeController(EmployeeRepositoryClass employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  @GetMapping("/employees")
  public Iterable<EmployeeModel> findAllEmployees() {

    /*- iterating 
    Iterable<EmployeeModel> employees = employeeRepository.findAll();
     
    for (EmployeeModel employee : employees) {
      System.out.println(employee.getFirstName() + " " + employee.getLastName());
    }
     */

    // Sort by id in ascending order
    return employeeRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));

    // return this.employeeRepository.findAll();
  }

  @GetMapping("/employees/{id}")
  public EmployeeModel findEmployeeById(@PathVariable Integer id) {
    EmployeeModel employee = employeeRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Employee with ID " + id + " not found"));

    if (employee == null) {
      throw new RuntimeException("Employee with" + id + "not found");
    }

    return employee;
  }

  @GetMapping("/employees/deletebyids")
  public void getEmployeesByIds(@RequestBody List<Integer> ids) {
    Iterable<EmployeeModel> employees = employeeRepository.findAllById(ids);
    for (EmployeeModel employee : employees) {
      System.out
          .println("ID: " + employee.getId() + ", Name: " + employee.getFirstName() + " " + employee.getLastName());
    }
  }

  @GetMapping("/paginatedemployees")
  public Page<EmployeeModel> paginatedEmployees(

      /*-
        url: http://localhost:5000/paginatedemployees?page=1&size=3&sortBy=firstName
       */
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "5") int size,
      @RequestParam(defaultValue = "firstName") String sortBy) {
    Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
    return employeeRepository.findAll(pageable);

  }

  @PostMapping("/employees")
  public EmployeeModel addOneEmployee(@RequestBody EmployeeModel employee) {
    return this.employeeRepository.save(employee);
  }

  @PutMapping("/employees/{id}")
  public EmployeeModel updateEmployee(@PathVariable Integer id, @RequestBody EmployeeModel employeeDetails) {
    EmployeeModel existingEmployee = employeeRepository.findById(id).orElse(null);

    if (existingEmployee == null) {
      throw new RuntimeException("Employee not found with id: " + id);
    }

    existingEmployee.setFirstName(employeeDetails.getFirstName());
    existingEmployee.setLastName(employeeDetails.getLastName());
    existingEmployee.setDateOfBirth(employeeDetails.getDateOfBirth());
    return employeeRepository.save(existingEmployee);
  }

  // Update specific fields of an employee (PATCH)
  @PatchMapping("/{id}")
  public EmployeeModel partialUpdateEmployee(@PathVariable Integer id, @RequestBody EmployeeModel employeeDetails) {
    EmployeeModel existingEmployee = employeeRepository.findById(id).orElse(null);

    if (existingEmployee == null) {
      throw new RuntimeException("Employee not found with id: " + id);
    }

    if (employeeDetails.getFirstName() != null) {
      existingEmployee.setFirstName(employeeDetails.getFirstName());
    }
    if (employeeDetails.getLastName() != null) {
      existingEmployee.setLastName(employeeDetails.getLastName());
    }
    if (employeeDetails.getDateOfBirth() != null) {
      existingEmployee.setDateOfBirth(employeeDetails.getDateOfBirth());
    }

    return employeeRepository.save(existingEmployee);
  }

  // Delete an employee by ID
  @DeleteMapping("/employees/{id}")
  public String deleteEmployee(@PathVariable Integer id) {
    employeeRepository.deleteById(id);
    return "Employee deleted successfully!";
  }

  @DeleteMapping("/employees")
  public void deleteEmployee(@RequestBody EmployeeModel employee) {
    employeeRepository.delete(employee);
  }

  @DeleteMapping("/employees/deletebatch")
  public void deleteEmployeesByIds(@RequestBody List<Integer> ids) {
    employeeRepository.deleteAllById(ids);
  }

  @DeleteMapping("/employees/delete")
  public void deleteMultipleEmployees(@RequestBody List<EmployeeModel> employees) {
    employeeRepository.deleteAll(employees);
  }

}