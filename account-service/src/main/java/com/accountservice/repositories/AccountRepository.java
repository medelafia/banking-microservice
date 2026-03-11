package com.accountservice.repositories;

import com.accountservice.entities.Account;
import com.accountservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface AccountRepository extends JpaRepository<Account, String> {
    List<Account> user(User user);

    List<Account> findAllByUserId(String userId);
}
