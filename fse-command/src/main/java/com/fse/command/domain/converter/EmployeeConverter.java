package com.fse.command.domain.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Component;

import com.fse.command.application.dto.CreateEmployeeRequest;
import com.fse.command.application.dto.SkillRequest;
import com.fse.command.domain.model.Employee;
import com.fse.command.domain.model.Skill;

@Component
public class EmployeeConverter {

	public Employee createEmployeeRequestRequestToEmployee(CreateEmployeeRequest req) {
		return Employee.builder()
			   .id(ThreadLocalRandom.current().nextInt(10000,20000))
			   .name(req.getName())
			   .email(req.getEmail())
			   .mobile(req.getMobile())
			   .technicalSkill(skillObjectConvertor(req.getTechnicalSkill()))
			   .nonTechnicalSkill(skillObjectConvertor(req.getNonTechnicalSkill()))
			   .creationDate(new Date())
			   .build();
	}
	
	public Employee updateEmployeeRequestRequestToEmployee(CreateEmployeeRequest req) {
		return Employee.builder()
			   .id(req.getId())
			   .technicalSkill(skillObjectConvertor(req.getTechnicalSkill()))
			   .creationDate(new Date())
			   .build();
	}
	
	public List<Skill> skillObjectConvertor(List<SkillRequest> list) {
		List<Skill> skillList = new ArrayList<Skill>();
		for (SkillRequest skillreq : list) {
			Skill skill = new Skill();
			skill.setSkillName(skillreq.getSkillName());
			skill.setSkillScore(skillreq.getSkillScore());
			skillList.add(skill);
		}
		return skillList;
	}
}
