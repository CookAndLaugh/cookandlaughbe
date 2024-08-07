package com.vansisto.cookandlaughbe.repository;

import com.vansisto.cookandlaughbe.entity.ActivationCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ActivationCodeRepository extends JpaRepository<ActivationCode, Long> {
    Optional<ActivationCode> findByCode(String code);
    boolean existsByCode(String code);
}
