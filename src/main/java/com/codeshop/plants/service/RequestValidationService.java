package com.codeshop.plants.service;

import org.springframework.stereotype.Service;

import com.codeshop.plants.dto.UserDTO;

@Service
public class RequestValidationService {

    public boolean userDtoIncomplete(UserDTO userDto) {
        return (userDto.getFirstName() == null || userDto.getLastName() == null || userDto.getStreet() == null
                || userDto.getCity() == null || userDto.getPostalCode() == null);
    }

    public boolean userDtoAddressEmpty(UserDTO userDto) {
        return (userDto.getStreet().isEmpty() && userDto.getCity().isEmpty() && userDto.getPostalCode().isEmpty());
    }

    public boolean userDtoAddressProvided(UserDTO userDto) {
        return (userDto.getStreet() != null || userDto.getCity() != null || userDto.getPostalCode() != null);
    }
}
