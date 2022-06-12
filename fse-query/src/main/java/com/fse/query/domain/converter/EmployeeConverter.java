package com.fse.query.domain.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fse.query.application.dto.EmployeeResponse;
import com.fse.query.application.dto.SkillResponse;
import com.fse.query.domain.model.Employee;
import com.fse.query.domain.model.Skill;

@Component
public class EmployeeConverter {

	public EmployeeResponse employeeToEmployeeResponse(Employee p) {
		return EmployeeResponse.builder()
			   .name(p.getName())
			   .email(p.getEmail())
			   .mobile(p.getMobile())
			   .technicalSkill(skillObjectConvertor(p.getTechnicalSkill()))
			   .nonTechnicalSkill(skillObjectConvertor(p.getNonTechnicalSkill()))
			   .build();
	}
	
	public List<SkillResponse> skillObjectConvertor(List<Skill> list) {
		List<SkillResponse> skillList = new ArrayList<SkillResponse>();
		for (Skill skillreq : list) {
			SkillResponse skillResp = new SkillResponse();
			skillResp.setSkillName(skillreq.getSkillName());
			skillResp.setSkillScore(skillreq.getSkillScore());
			skillList.add(skillResp);
		}
		return skillList;
	}
}
