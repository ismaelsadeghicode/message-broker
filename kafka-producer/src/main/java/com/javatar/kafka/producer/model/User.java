package com.javatar.kafka.producer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright 2020 (C) fwutech.com
 *
 * @author : Esmaeil Sadeghi
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String name;
    private String dept;
    private Long salary;
}
