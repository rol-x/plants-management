package com.codeshop.plants.user;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class UserDTO {

    private String firstName;
    private String lastName;
    private String street;
    private String city;
    private String postalCode;
}
