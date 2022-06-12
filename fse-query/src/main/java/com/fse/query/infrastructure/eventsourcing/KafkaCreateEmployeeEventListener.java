package com.fse.query.infrastructure.eventsourcing;

import java.util.concurrent.CountDownLatch;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fse.query.domain.model.Employee;
import com.fse.query.domain.service.FindEmployeeService;
import com.google.gson.Gson;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class KafkaCreateEmployeeEventListener {

    @Autowired
    private FindEmployeeService findEmployeeService;
    private final CountDownLatch latch = new CountDownLatch(3);

    @KafkaListener(topics = "${message.topic.createEmployee}")
    public void listen(ConsumerRecord<String, String> stringStringConsumerRecord) throws Exception {
        Employee employee = new Gson().fromJson(stringStringConsumerRecord.value(), Employee.class);
        findEmployeeService.createEmployee(employee);
        log.info("Insert employee {} in reader database", employee);
        latch.countDown();
    }
}
