package com.fse.command.domain.converter;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fse.command.application.dto.CreateEmployeeRequest;
import com.fse.command.application.dto.CreateEmployeeRequest.CreateEmployeeRequestBuilder;
import com.fse.command.application.dto.SkillRequest;
import com.fse.command.application.dto.SkillRequest.SkillRequestBuilder;

@ExtendWith(MockitoExtension.class)
class EmployeeConverterTest {

	@InjectMocks
	EmployeeConverter employeeConverter;

	@Test
	void createEmployeeRequestRequestToEmployeeTest() {
		SkillRequestBuilder skillRequestBuilder = SkillRequest.builder().skillName("java").skillScore(100);

		List<SkillRequest> list = new ArrayList<SkillRequest>();
		list.add(skillRequestBuilder.build());

		CreateEmployeeRequestBuilder reqBuilder = CreateEmployeeRequest.builder().technicalSkill(list).nonTechnicalSkill(list);
		employeeConverter.createEmployeeRequestRequestToEmployee(reqBuilder.build());
	}

}
