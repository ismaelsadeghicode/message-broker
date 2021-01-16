package com.javatar.kafka.consumer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

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
