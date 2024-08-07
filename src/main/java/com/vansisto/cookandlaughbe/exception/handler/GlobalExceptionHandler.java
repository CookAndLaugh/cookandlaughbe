package com.vansisto.cookandlaughbe.exception.handler;

import com.vansisto.cookandlaughbe.exception.ActivationCodeExpiredException;
import com.vansisto.cookandlaughbe.exception.already_exists.AlreadyExistsException;
import com.vansisto.cookandlaughbe.exception.misc.ExceptionResponse;
import com.vansisto.cookandlaughbe.exception.not_found.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.GONE;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ResponseStatus(FORBIDDEN)
    @ExceptionHandler({InsufficientAuthenticationException.class, BadCredentialsException.class})
    public ExceptionResponse handleAuthenticationException(Exception ex, HttpServletRequest request) {
        return new ExceptionResponse(request.getRequestURI(), ex.getMessage(), FORBIDDEN.value(), LocalDateTime.now());
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ExceptionResponse handleNotFoundException(NotFoundException ex, HttpServletRequest request) {
        return new ExceptionResponse(request.getRequestURI(), ex.getMessage(), NOT_FOUND.value(), LocalDateTime.now());
    }

    @ResponseStatus(GONE)
    @ExceptionHandler(ActivationCodeExpiredException.class)
    public ExceptionResponse handleActivationCodeExpiredException(ActivationCodeExpiredException ex, HttpServletRequest request) {
        return new ExceptionResponse(request.getRequestURI(), ex.getMessage(), GONE.value(), LocalDateTime.now());
    }

    @ResponseStatus(CONFLICT)
    @ExceptionHandler(AlreadyExistsException.class)
    public ExceptionResponse handleNotFoundException(AlreadyExistsException ex, HttpServletRequest request) {
        return new ExceptionResponse(request.getRequestURI(), ex.getMessage(), CONFLICT.value(), LocalDateTime.now());
    }
}
