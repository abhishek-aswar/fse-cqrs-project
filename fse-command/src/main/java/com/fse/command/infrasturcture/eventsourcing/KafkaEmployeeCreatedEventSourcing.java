package com.fse.command.infrasturcture.eventsourcing;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fse.command.domain.model.Employee;
import com.fse.command.infrasturcture.eventsourcing.events.EmployeeCreatedEvent;

import lombok.val;
import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class KafkaEmployeeCreatedEventSourcing {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value(value = "${message.topic.createEmployee}")
    private String topicName;

    public EmployeeCreatedEvent publicCreateEmployeeEvent(Employee employee, String eventType) throws JsonProcessingException {
        val id = UUID.randomUUID();
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        val json = objectWriter.writeValueAsString(employee);
        log.info("Send json '{}' to topic {}", json, topicName);
        kafkaTemplate.send(topicName, json);
        return EmployeeCreatedEvent.builder().uuid(id).eventType(eventType).employee(employee).build();
    }

}
