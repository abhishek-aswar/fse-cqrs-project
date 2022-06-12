package com.fse.query.application.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class SkillResponse {
    private String skillName;
    private Integer skillScore;
}
