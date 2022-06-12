package com.fse.command.infrasturcture.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.fse.command.domain.model.Employee;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, Integer> {
}
