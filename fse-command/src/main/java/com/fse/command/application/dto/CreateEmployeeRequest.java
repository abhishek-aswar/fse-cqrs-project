package com.fse.command.application.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateEmployeeRequest {

	private int id;
	
	@Size(max=30, min=5, message="Name criteria not met")
	private String name;
	
	@Email
	private String email;
	
	@Size(max=10, min=10, message="Mobile criteria not met")
	@Digits(fraction = 0, integer = 10)
	private String mobile;
	
	@Valid
    private List<SkillRequest> technicalSkill;
	
	@Valid
    private List<SkillRequest> nonTechnicalSkill;

}
