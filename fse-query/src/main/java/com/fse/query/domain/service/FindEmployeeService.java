package com.fse.query.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.fse.query.application.dto.EmployeeResponse;
import com.fse.query.domain.converter.EmployeeConverter;
import com.fse.query.domain.exception.EmployeeNotFoundException;
import com.fse.query.domain.model.Employee;
import com.fse.query.domain.model.Employee.EmployeeBuilder;
import com.fse.query.infrastructure.repository.EmployeeRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class FindEmployeeService {

	@Autowired
	private MongoTemplate mongoTemplate;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeConverter employeeConverter;

    public EmployeeResponse findByName(String name) throws EmployeeNotFoundException {
        Employee employee = employeeRepository.findByName(name).orElseThrow(() -> new EmployeeNotFoundException(name, "Employee not found"));
        log.info("Find employee: {}", employee);
        return employeeConverter.employeeToEmployeeResponse(employee);
    }

    public void createEmployee(Employee p) {
        log.info("Insert new employee: {}", p);
        employeeRepository.save(p);
    }

	public List<Employee> employeeSearch(String name, String email, String mobile, String skillName) throws EmployeeNotFoundException {
		
		List<Employee> listReturn = new ArrayList<Employee>();
		
		Query query = new Query();
		query.addCriteria(Criteria.where("name").regex("^"+name));
		query.addCriteria(Criteria.where("email").is(email));
		query.addCriteria(Criteria.where("mobile").is(mobile));
		List<Employee> list = mongoTemplate.find(query, Employee.class, "employee");
		
		List<Employee.EmployeeBuilder> list2;
		
		if(!StringUtils.isEmpty(skillName)) {
			list2 = list.stream().map(empList -> Employee.builder().id(empList.getId()).name(empList.getName())
                    .email(empList.getEmail()).mobile(empList.getMobile()).creationDate(empList.getCreationDate())
                    .technicalSkill(empList.getTechnicalSkill().stream().filter(s -> skillName.equalsIgnoreCase(s.getSkillName())).filter(s -> s.getSkillScore() > 10).collect(Collectors.toList()))
                    .nonTechnicalSkill(empList.getNonTechnicalSkill().stream().filter(s -> skillName.equalsIgnoreCase(s.getSkillName())).filter(s -> s.getSkillScore() > 10).collect(Collectors.toList()))).collect(Collectors.toList());
		}else {
			list2 = list.stream().map(empList -> Employee.builder().id(empList.getId()).name(empList.getName())
                    .email(empList.getEmail()).mobile(empList.getMobile()).creationDate(empList.getCreationDate())
                    .technicalSkill(empList.getTechnicalSkill().stream().filter(s -> s.getSkillScore() > 10).collect(Collectors.toList()))
                    .nonTechnicalSkill(empList.getNonTechnicalSkill().stream().filter(s -> s.getSkillScore() > 10).collect(Collectors.toList()))).collect(Collectors.toList());
		}		

		for (EmployeeBuilder employeeBuilder : list2) {
			listReturn.add(employeeBuilder.build());
		}
		
		return listReturn;
	}
	

}
