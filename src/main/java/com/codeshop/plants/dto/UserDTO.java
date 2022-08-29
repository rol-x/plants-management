package com.codeshop.plants.dto;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class UserDTO {

    private String firstName;
    private String lastName;
    private String address;
}