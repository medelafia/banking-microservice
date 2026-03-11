package com.userservice;

import com.userservice.entities.User;
import com.userservice.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(UserRepository userRepository) {
        return args -> {
            userRepository.save(User.builder()
                            .id(UUID.randomUUID().toString())
                            .email("mohamedelafia723@gmail.com")
                            .firstName("Mohamed")
                            .lastName("El Afia")
                            .phoneNumber("+212-658-045-721")
                    .build());
        } ;
    }
}
