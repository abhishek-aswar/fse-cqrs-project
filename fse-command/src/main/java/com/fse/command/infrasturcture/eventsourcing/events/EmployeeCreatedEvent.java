package com.fse.command.infrasturcture.eventsourcing.events;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

import com.fse.command.domain.model.Employee;

@Builder
@Data
public class EmployeeCreatedEvent {
    private UUID uuid;
    private String eventType;
    private Employee employee;
}
