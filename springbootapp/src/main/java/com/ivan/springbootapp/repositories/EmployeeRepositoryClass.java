package com.ivan.springbootapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ivan.springbootapp.models.EmployeeModel;

/*- befores
public interface EmployeeRepositoryClass extends CrudRepository<EmployeeModel, Integer> {
  public interface EmployeeRepositoryClass extends PagingAndSortingRepository<EmployeeModel, Integer> {
    
  */
@Repository
public interface EmployeeRepositoryClass extends JpaRepository<EmployeeModel, Integer> {

}
