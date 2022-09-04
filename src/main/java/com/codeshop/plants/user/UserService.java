package com.codeshop.plants.user;

import java.util.List;

import org.springframework.stereotype.Service;

import com.codeshop.plants.exception.EntityNotFoundException;

@Service
public interface UserService {

    public User getUser(Long userId) throws EntityNotFoundException;

    public List<User> getAllUsers();

    public User addUser(UserDTO userDto);

    public User updateUser(Long userId, UserDTO userDto) throws EntityNotFoundException;

    public User partiallyUpdateUser(Long userId, UserDTO userDto) throws EntityNotFoundException;

    public void deleteUser(Long userId) throws EntityNotFoundException;
}
