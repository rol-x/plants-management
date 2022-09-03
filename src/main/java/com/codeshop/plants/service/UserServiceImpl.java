package com.codeshop.plants.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeshop.plants.dto.UserDTO;
import com.codeshop.plants.exception.EntityNotFoundException;
import com.codeshop.plants.exception.IncompleteUserException;
import com.codeshop.plants.model.Address;
import com.codeshop.plants.model.User;
import com.codeshop.plants.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User getUser(Long userId) throws EntityNotFoundException {
        return userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User addUser(UserDTO userDto) throws IncompleteUserException {
        if (userDto.getFirstName() == null || userDto.getLastName() == null)
            throw new IncompleteUserException();
        return userRepository.save(new User(userDto));
    }

    @Override
    public User updateUser(Long userId, UserDTO userDto)
            throws EntityNotFoundException, IncompleteUserException {
        if (userRepository.findById(userId).isEmpty())
            throw new EntityNotFoundException();
        if (userDto.getFirstName() == null || userDto.getLastName() == null)
            throw new IncompleteUserException();

        userRepository.updateFirstName(userId, userDto.getFirstName());
        userRepository.updateLastName(userId, userDto.getLastName());
        userRepository.updateAddress(userId,
                new Address(userDto.getStreet(), userDto.getCity(), userDto.getPostalCode()));
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public User partiallyUpdateUser(Long userId, UserDTO userDto) throws EntityNotFoundException {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty())
            throw new EntityNotFoundException();
        Address currentAddress = userOptional.get().getAddress();

        if (userDto.getFirstName() != null)
            userRepository.updateFirstName(userId, userDto.getFirstName());
        if (userDto.getLastName() != null)
            userRepository.updateLastName(userId, userDto.getLastName());

        if (userDto.getStreet() != null)
            currentAddress.setStreet(userDto.getStreet());
        if (userDto.getCity() != null)
            currentAddress.setCity(userDto.getCity());
        if (userDto.getPostalCode() != null)
            currentAddress.setPostalCode(userDto.getPostalCode());

        userRepository.updateAddress(userId, currentAddress);
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public void deleteUser(Long userId) throws EntityNotFoundException {
        if (userRepository.findById(userId).isEmpty())
            throw new EntityNotFoundException();

        userRepository.deleteById(userId);
    }

}
