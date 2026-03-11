package com.accountservice.repositories;


import com.accountservice.entities.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("user-service")
public interface UserRepository {
    @GetMapping("/api/v1/user/{userId}")
    public ResponseEntity<User> findById(@PathVariable String userId);
}
