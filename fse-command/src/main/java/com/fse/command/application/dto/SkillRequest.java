package com.fse.command.application.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SkillRequest {
	
	@NotBlank
    private String skillName;
	
	@Min(0)
	@Max(20)
    private Integer skillScore;
}
