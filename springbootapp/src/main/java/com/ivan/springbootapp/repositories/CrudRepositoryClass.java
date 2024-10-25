package com.ivan.springbootapp.repositories;

import java.util.Optional;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

@NoRepositoryBean
public interface CrudRepositoryClass<T, ID> extends Repository<T, ID> {

  <S extends T> S save(S entity);

  <S extends T> Iterable<S> saveAll(Iterable<S> entities);

  Optional<T> findById(ID id);

  boolean existsById(ID id);

  Iterable<T> findAll();

  Iterable<T> findAllById(Iterable<ID> ids);

  long count();

  void deleteById(ID id);

  void delete(T entity);

  void deleteAllById(Iterable<? extends ID> ids);

  void deleteAll(Iterable<? extends T> entities);

  void deleteAll();
}

/*-

- The @Repository annotation on EmployeeRepositoryClass tells Spring to treat this interface as a Data Access Object (DAO).
- The @NoRepositoryBean on CrudRepositoryClass tells Spring that this interface is not meant to be instantiated directly but can be extended by other repository interfaces (like EmployeeRepositoryClass).

 */