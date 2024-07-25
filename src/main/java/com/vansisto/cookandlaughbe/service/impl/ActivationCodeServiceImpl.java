package com.vansisto.cookandlaughbe.service.impl;

import com.vansisto.cookandlaughbe.config.properties.app.registration.ActivationCodePropertyConfig;
import com.vansisto.cookandlaughbe.entity.ActivationCode;
import com.vansisto.cookandlaughbe.entity.User;
import com.vansisto.cookandlaughbe.repository.ActivationCodeRepository;
import com.vansisto.cookandlaughbe.service.ActivationCodeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class ActivationCodeServiceImpl implements ActivationCodeService {

    private final ActivationCodePropertyConfig activationCodePropertyConfig;
    private final ActivationCodeRepository activationCodeRepository;

    @Override
    @Transactional
    public String generateAndSaveActivationCode(User user) {
        String generatedCode;
        do {
            generatedCode = generateActivationCode(6);
        } while (activationCodeRepository.existsByCode(generatedCode));

        ActivationCode newActivationCode = new ActivationCode()
                .setCode(generatedCode)
                .setCreatedAt(LocalDateTime.now())
                .setExpiresAt(LocalDateTime.now().plusMinutes(activationCodePropertyConfig.getExpirationMinutes()))
                .setUser(user);
        activationCodeRepository.save(newActivationCode);
        return generatedCode;
    }

    private String generateActivationCode(int length) {
        String characters = "0123456789";
        StringBuilder codeBuilder = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();
        for (int i = 0; i < length; i++) {
            int randomIndex = secureRandom.nextInt(characters.length());
            codeBuilder.append(characters.charAt(randomIndex));
        }
        return codeBuilder.toString();
    }
}
