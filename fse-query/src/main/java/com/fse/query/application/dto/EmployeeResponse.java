package com.fse.query.application.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeResponse {
    private String name;
    private String email;
    private String mobile;
    private List<SkillResponse> technicalSkill;
    private List<SkillResponse> nonTechnicalSkill;
}
