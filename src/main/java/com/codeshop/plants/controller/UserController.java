package com.codeshop.plants.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codeshop.plants.dto.UserDTO;
import com.codeshop.plants.exception.EntityNotFoundException;
import com.codeshop.plants.exception.IncompleteUserException;
import com.codeshop.plants.model.User;
import com.codeshop.plants.service.UserService;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("{id}")
    public User getUser(@PathVariable("id") Long id) throws EntityNotFoundException {
        return userService.getUser(id);
    }

    @GetMapping("")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("")
    public ResponseEntity<User> addUser(@RequestBody UserDTO userDto)
            throws IncompleteUserException {
                User savedUser = userService.addUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Content-Location", "/users/" + savedUser.getId().toString()).body(savedUser);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable("id") Long id, @RequestBody UserDTO userDto)
            throws EntityNotFoundException, IncompleteUserException {
        return userService.updateUser(id, userDto);
    }

    @PatchMapping("/{id}")
    public User partiallyUpdateUser(@PathVariable("id") Long id, @RequestBody UserDTO userDto)
            throws EntityNotFoundException {
        return userService.partiallyUpdateUser(id, userDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id)
            throws EntityNotFoundException {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
