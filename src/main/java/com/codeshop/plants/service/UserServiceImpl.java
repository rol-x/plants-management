package com.codeshop.plants.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeshop.plants.dto.UserDTO;
import com.codeshop.plants.exception.EntityNotFoundException;
import com.codeshop.plants.exception.IncompleteUserException;
import com.codeshop.plants.model.Address;
import com.codeshop.plants.model.User;
import com.codeshop.plants.repository.AddressRepository;
import com.codeshop.plants.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    RequestValidationService requestValidationService;

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
        validateRequestFormat(userDto);

        // Don't save empty Address object when no address data is provided
        if (requestValidationService.userDtoAddressEmpty(userDto))
            return userRepository.save(new User(userDto));
        else {
            Address address = addressRepository.save(new Address(userDto));
            return userRepository.save(new User(userDto, address));
        }
    }

    @Override
    public User updateUser(Long userId, UserDTO userDto) throws EntityNotFoundException, IncompleteUserException {
        validateRequestFormat(userDto);

        User user = getUser(userId);
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());

        // Update or create the Address object only when address data is provided
        if (!requestValidationService.userDtoAddressEmpty(userDto)) {
            Address address = user.getAddress() == null ? new Address() : user.getAddress();
            address.setStreet(userDto.getStreet());
            address.setCity(userDto.getCity());
            address.setPostalCode(userDto.getPostalCode());
            addressRepository.save(address);
            user.setAddress(address);
        }
        return userRepository.save(user);
    }

    @Override
    public User partiallyUpdateUser(Long userId, UserDTO userDto) throws EntityNotFoundException {
        // Update only the attributes present in the request
        User user = getUser(userId);
        if (userDto.getFirstName() != null)
            user.setFirstName(userDto.getFirstName());
        if (userDto.getLastName() != null)
            user.setLastName(userDto.getLastName());

        // Update or create the Address object only when some address data is provided
        if (requestValidationService.userDtoAddressProvided(userDto)) {
            Address address = user.getAddress() == null ? new Address() : user.getAddress();
            if (userDto.getStreet() != null)
                address.setStreet(userDto.getStreet());
            if (userDto.getCity() != null)
                address.setCity(userDto.getCity());
            if (userDto.getPostalCode() != null)
                address.setPostalCode(userDto.getPostalCode());
            addressRepository.save(address);
            user.setAddress(address);
        }
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) throws EntityNotFoundException {
        if (userRepository.findById(userId).isEmpty())
            throw new EntityNotFoundException();

        // Address entity deletion is handled automatically via @OneToOne annotation
        userRepository.deleteById(userId);
    }

    private void validateRequestFormat(UserDTO userDto) {
        if (requestValidationService.userDtoIncomplete(userDto))
            throw new IncompleteUserException();
    }

}
