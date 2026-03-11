package com.userservice.services;


import com.userservice.entities.NotificationEvent;
import com.userservice.entities.User;
import com.userservice.enums.NotificationEventType;
import com.userservice.events.UserCreationEvent;
import com.userservice.exceptions.UserNotFoundException;
import com.userservice.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class UserServices {
    private final UserRepository userRepository;
    private final KafkaTemplate<String, NotificationEvent> kafkaTemplate;
    private final static String TOPIC = "notifications-topic";

    public UserServices(UserRepository userRepository, KafkaTemplate<String, NotificationEvent> kafkaTemplate) {
        this.userRepository = userRepository;
        this.kafkaTemplate = kafkaTemplate;
    }
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }
    public User findUserById(String userId) {
        System.out.println(userId);
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
    }
    public User createUser(User user) {
        user.setId(UUID.randomUUID().toString());
        this.kafkaTemplate.send(
                TOPIC ,
                NotificationEvent.builder()
                        .eventId(UUID.randomUUID().toString())
                        .message("Welcome , The User created successfully , your id : "+ user.getId())
                        .notificationEventType(NotificationEventType.USER_CREATED_EVENT)
                        .timestamp(Timestamp.from(Instant.now()))
                        .email(user.getEmail())
                        .build()
                ) ;

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
