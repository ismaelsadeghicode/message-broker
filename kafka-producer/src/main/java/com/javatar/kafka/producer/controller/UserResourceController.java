package com.javatar.kafka.producer.controller;

import com.javatar.kafka.producer.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Copyright 2020 (C) fwutech.com
 *
 * @author : Esmaeil Sadeghi
 */
@RestController
@RequestMapping("kafka")
@Slf4j
public class UserResourceController {

    private final KafkaTemplate<String, User> kafkaTemplate;
//    private KafkaTemplate<String, String> kafkaTemplate; (1)
    private static final String TOPIC = "Kafka_Example";

    public UserResourceController(KafkaTemplate<String, User> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
//    private static final String TOPIC = "kafkasample";

    @GetMapping("/publish/{name}")
    public String post(@PathVariable("name") String name){
//    @GetMapping("/publish/{message}")  (1)
//    public String post(@PathVariable("message") String message){  (1)
//        kafkaTemplate.send(TOPIC, new User(name, "Tecnology", 12000L));
//        kafkaTemplate.send(TOPIC, message);  (1)

        List<User> users = new ArrayList<>();
        for (long i = 1; i < 10000; i++) {
            User user = new User();
            user.setDept("test " + i);
            user.setName("test " + i);
            user.setSalary(i);
            users.add(user);
        }
        for (User user:
             users) {
            send(TOPIC, user);
        }
        return "Publish Successfuly!";
    }

    @Async
    public void send(String topic, User message) {
        ListenableFuture<SendResult<String, User>> future = kafkaTemplate.send(topic, message);
        future.addCallback(new ListenableFutureCallback<SendResult<String, User>>() {

            @Override
            public void onSuccess(final SendResult<String, User> message) {
                log.info("sent message= " + message + " with offset= " + message.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(final Throwable throwable) {
                log.error("unable to send message= " + message, throwable);
            }
        });
    }

    @GetMapping("/publish")
    public Date getDate(){
        return new Date();
    }

}
