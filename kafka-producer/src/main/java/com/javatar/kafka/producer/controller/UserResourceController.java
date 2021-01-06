package com.javatar.kafka.producer.controller;

import com.javatar.kafka.producer.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.crypto.Data;
import java.util.Date;

@RestController
@RequestMapping("kafka")
public class UserResourceController {

    @Autowired
    private KafkaTemplate<String, User> kafkaTemplate;
//    private KafkaTemplate<String, String> kafkaTemplate; (1)
    private static final String TOPIC = "kafka_example";

    @GetMapping("/publish/{name}")
    public String post(@PathVariable("name") String name){
//    @GetMapping("/publish/{message}")  (1)
//    public String post(@PathVariable("message") String message){  (1)
        kafkaTemplate.send(TOPIC, new User(name, "Tecnology", 12000L));
//        kafkaTemplate.send(TOPIC, message);  (1)
        return "Publish Successfuly!";
    }

    @GetMapping("/publish")
    public Date getDate(){
        return new Date();
    }

}
