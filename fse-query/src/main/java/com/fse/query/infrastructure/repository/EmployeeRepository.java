package com.fse.query.infrastructure.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.fse.query.domain.model.Employee;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, Integer> {

	Optional<Employee> findByName(String name);

}
