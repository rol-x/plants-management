package com.codeshop.plants.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.codeshop.plants.dto.UserDTO;
import com.codeshop.plants.exception.EntityNotFoundException;
import com.codeshop.plants.model.User;

@Service
public interface UserService {

    public User getUser(Long id) throws EntityNotFoundException;

    public List<User> getAllUsers();

    public User addUser(UserDTO userDto);

    public User updateUser(Long id, UserDTO userDto) throws EntityNotFoundException;

    public User partiallyUpdateUser(Long id, UserDTO userDto) throws EntityNotFoundException;

    public void deleteUser(Long id) throws EntityNotFoundException;
}
