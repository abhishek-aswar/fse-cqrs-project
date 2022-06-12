package com.fse.query.domain.model;

import java.util.List;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Employee {

    @Id
    private int id;
    private String name;
    private String email;
    private String mobile;
    private List<Skill> technicalSkill;
    private List<Skill> nonTechnicalSkill;
    private String creationDate;

}
