package com.vansisto.cookandlaughbe.controller;

import com.vansisto.cookandlaughbe.dto.LoginRequest;
import com.vansisto.cookandlaughbe.dto.LoginResponse;
import com.vansisto.cookandlaughbe.dto.RegistrationRequest;
import com.vansisto.cookandlaughbe.security.AuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.ACCEPTED;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication")
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    @ResponseStatus(ACCEPTED)
    public void register(@Valid @RequestBody RegistrationRequest request) throws MessagingException {
        authenticationService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Valid LoginRequest loginRequest) {
        return authenticationService.login(loginRequest);
    }

    @GetMapping("/confirm-registration")
    public void confirmRegistration(@RequestParam String code) {
        authenticationService.confirmRegistration(code);
    }
}
