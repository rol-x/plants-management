package com.codeshop.plants.dto;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class CustomerDTO {

    private String firstName;
    private String lastName;
    private String address;
}
