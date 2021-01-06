package com.javatar.kafka.producer.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private String name;
    private String dept;
    private Long salary;
}
