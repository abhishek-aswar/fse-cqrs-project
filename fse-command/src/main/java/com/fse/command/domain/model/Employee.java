package com.fse.command.domain.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;

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
    private Date creationDate;
}
