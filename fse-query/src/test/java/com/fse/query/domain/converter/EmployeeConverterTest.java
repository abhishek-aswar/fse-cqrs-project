package com.fse.query.domain.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fse.query.application.dto.EmployeeResponse;
import com.fse.query.domain.model.Employee;
import com.fse.query.domain.model.Employee.EmployeeBuilder;
import com.fse.query.domain.model.Skill;

@ExtendWith(MockitoExtension.class)
class EmployeeConverterTest {

	@InjectMocks
	EmployeeConverter employeeConverter;

	@Test
	void employeeToEmployeeResponseTest() {
		Skill skill = new Skill();
		skill.setSkillName("Java");

		List<Skill> skillList = new ArrayList<Skill>();
		skillList.add(skill);

		EmployeeBuilder employee = Employee.builder().name("Abhishek").technicalSkill(skillList).nonTechnicalSkill(skillList);

		EmployeeResponse employeeResponse = employeeConverter.employeeToEmployeeResponse(employee.build());
		assertEquals(employeeResponse.getName(), employee.build().getName());
	}

}
