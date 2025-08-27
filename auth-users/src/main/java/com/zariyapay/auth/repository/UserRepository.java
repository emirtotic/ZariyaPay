package com.zariyapay.auth.repository;

import com.zariyapay.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserById(Long id);
    void deleteUserById(Long id);
}
