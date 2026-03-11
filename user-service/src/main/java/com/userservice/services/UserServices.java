package com.userservice.services;


import com.userservice.entities.User;
import com.userservice.events.UserCreationEvent;
import com.userservice.exceptions.UserNotFoundException;
import com.userservice.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServices {
    private final UserRepository userRepository;
    //private final KafkaTemplate<String, UserCreationEvent> kafkaTemplate;

    public UserServices(UserRepository userRepository ) {
        this.userRepository = userRepository;
    }
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }
    public User findUserById(String userId) {
        System.out.println(userId);
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
    }
    public User createUser(User user) {
        return userRepository.save(user);
    }
    public User updateUser(User user) {
        return userRepository.save(user);
    }
    public void deleteUser(String id) {
        if(!userRepository.existsById(id)) {
            throw new UserNotFoundException("User not found");
        }
        userRepository.deleteById(id);
    }

}
