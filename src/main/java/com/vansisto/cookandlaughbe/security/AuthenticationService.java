package com.vansisto.cookandlaughbe.security;

import com.vansisto.cookandlaughbe.dto.LoginRequest;
import com.vansisto.cookandlaughbe.dto.LoginResponse;
import com.vansisto.cookandlaughbe.dto.RegistrationRequest;
import com.vansisto.cookandlaughbe.entity.ActivationCode;
import com.vansisto.cookandlaughbe.entity.Role;
import com.vansisto.cookandlaughbe.entity.User;
import com.vansisto.cookandlaughbe.exception.ActivationCodeExpiredException;
import com.vansisto.cookandlaughbe.exception.already_exists.UserAlreadyRegisteredException;
import com.vansisto.cookandlaughbe.exception.not_found.ActivationCodeNotFoundException;
import com.vansisto.cookandlaughbe.exception.not_found.RoleNotFoundException;
import com.vansisto.cookandlaughbe.repository.ActivationCodeRepository;
import com.vansisto.cookandlaughbe.repository.RoleRepository;
import com.vansisto.cookandlaughbe.repository.UserRepository;
import com.vansisto.cookandlaughbe.service.MailService;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class AuthenticationService {

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final MailService mailService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final ActivationCodeRepository activationCodeRepository;

    @Transactional
    public void register(RegistrationRequest request) throws MessagingException {
        String email = request.getEmail();

        if (userRepository.existsByEmailAndEnabledIsTrue(email)) {
            throw new UserAlreadyRegisteredException(email);
        } else {
            final String USER_ROLE_NAME = "USER";
            Role userRole = roleRepository.findByName(USER_ROLE_NAME)
                    .orElseThrow(() -> new RoleNotFoundException(USER_ROLE_NAME));

            User user = userRepository
                    .findByEmail(email)
                    .orElse(new User()
                            .setEmail(email)
                            .setLocked(false)
                            .setEnabled(false)
                            .setRoles(List.of(userRole)));

            user.setPassword(passwordEncoder.encode(request.getPassword()));

            userRepository.save(user);
            mailService.sendValidationEmail(user);
        }
    }

    public LoginResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        Map<String, Object> claims = new HashMap<>();
        User loggedInUser = (User) authentication.getPrincipal();
        fillClaims(loggedInUser, claims);
        String jwtToken = jwtService.generateToken(loggedInUser, claims);
        return new LoginResponse().setToken(jwtToken);
    }

    @Transactional
    public void confirmRegistration(String code) {
        ActivationCode activationCode = activationCodeRepository.findByCode(code)
                .orElseThrow(() -> new ActivationCodeNotFoundException(code));
        User user = activationCode.getUser();
        validateUser(user);
        validateCode(activationCode);

        activationCode.setValidatedAt(LocalDateTime.now());
        user.setEnabled(true);
    }

    private static void fillClaims(User loggedInUser, Map<String, Object> claims) {
        if (Objects.nonNull(loggedInUser.getFirstname())) {
            claims.put("firstname", loggedInUser.getFirstname());
        }
        if (Objects.nonNull(loggedInUser.getLastname())) {
            claims.put("lastname", loggedInUser.getLastname());
        }
    }

    private static void validateCode(ActivationCode activationCode) {
        if (activationCode.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new ActivationCodeExpiredException();
        }
    }

    private static void validateUser(User user) {
        if (user.isEnabled()) {
            throw new UserAlreadyRegisteredException();
        }
    }
}
