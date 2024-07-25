package com.vansisto.cookandlaughbe.repository;

import com.vansisto.cookandlaughbe.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmailAndEnabledIsTrue(String email);
}
