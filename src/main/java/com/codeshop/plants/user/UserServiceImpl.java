package com.codeshop.plants.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeshop.plants.address.Address;
import com.codeshop.plants.address.AddressRepository;
import com.codeshop.plants.exception.EntityNotFoundException;
import com.codeshop.plants.exception.IncompleteUserException;
import com.codeshop.plants.validation.RequestValidationService;

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
    public User addUser(UserDTO userDto) {
        validateRequestFormat(userDto);
        Address address = addressRepository.save(new Address(userDto));
        return userRepository.save(new User(userDto, address));
    }

    @Override
    public User updateUser(Long userId, UserDTO userDto) throws EntityNotFoundException {
        validateRequestFormat(userDto);

        User user = getUser(userId);
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());

        Address address = user.getAddress();
        address.setStreet(userDto.getStreet());
        address.setCity(userDto.getCity());
        address.setPostalCode(userDto.getPostalCode());

        addressRepository.save(address);
        user.setAddress(address);
        return userRepository.save(user);
    }

    @Override
    public User partiallyUpdateUser(Long userId, UserDTO userDto) throws EntityNotFoundException {
        User user = getUser(userId);
        Address address = user.getAddress();

        // Update only the attributes present in the request
        if (userDto.getFirstName() != null)
            user.setFirstName(userDto.getFirstName());
        if (userDto.getLastName() != null)
            user.setLastName(userDto.getLastName());
        if (userDto.getStreet() != null)
            address.setStreet(userDto.getStreet());
        if (userDto.getCity() != null)
            address.setCity(userDto.getCity());
        if (userDto.getPostalCode() != null)
            address.setPostalCode(userDto.getPostalCode());

        addressRepository.save(address);
        user.setAddress(address);
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) throws EntityNotFoundException {
        if (userRepository.findById(userId).isEmpty())
            throw new EntityNotFoundException();

        // Address deletion is handled automatically via @OneToOne annotation
        userRepository.deleteById(userId);
    }

    private void validateRequestFormat(UserDTO userDto) {
        if (requestValidationService.userDtoIncomplete(userDto))
            throw new IncompleteUserException();
    }
}
