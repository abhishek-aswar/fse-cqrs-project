package com.fse.command.domain.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fse.command.application.dto.CreateEmployeeRequest;
import com.fse.command.domain.converter.EmployeeConverter;
import com.fse.command.domain.model.Employee;
import com.fse.command.infrasturcture.eventsourcing.KafkaEmployeeCreatedEventSourcing;
import com.fse.command.infrasturcture.eventsourcing.events.EmployeeCreatedEvent;
import com.fse.command.infrasturcture.repository.EmployeeRepository;

import lombok.val;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class CreateEmployeeService {

	@Autowired
	private MongoTemplate mongoTemplate;
	@Autowired
	private EmployeeConverter employeeConverter;
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private KafkaEmployeeCreatedEventSourcing kafkaEmployeeCreatedEventSourcing;
	
	private static final long MILLIS_IN_A_DAY = 1000 * 60 * 60 * 24;

	public EmployeeCreatedEvent create(CreateEmployeeRequest request) {
		log.info("Creating new employee");
		val employee = employeeConverter.createEmployeeRequestRequestToEmployee(request);
		employeeRepository.save(employee);
		try {
			return kafkaEmployeeCreatedEventSourcing.publicCreateEmployeeEvent(employee, "create");
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}
	
	public EmployeeCreatedEvent updateEmp(CreateEmployeeRequest request) {
		log.info("Creating new employee");
		val employee = employeeConverter.updateEmployeeRequestRequestToEmployee(request);

		Query query = new Query();

		query.addCriteria(Criteria.where("id").is(request.getId()));
		query.addCriteria(Criteria.where("creationDate").lt(findTenDayBack(new Date())));

		Update update = new Update();
		update.set("technicalSkill", employee.getTechnicalSkill());
		update.set("creationDate", new Date());

		Employee empObject = mongoTemplate.findAndModify(query, update, Employee.class);

		log.info("Employee Data Modified");
		try {
			return kafkaEmployeeCreatedEventSourcing.publicCreateEmployeeEvent(empObject, "update");
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	private static Date findTenDayBack(Date date) {
		return new Date(date.getTime() - 10 * MILLIS_IN_A_DAY);
	}
}
