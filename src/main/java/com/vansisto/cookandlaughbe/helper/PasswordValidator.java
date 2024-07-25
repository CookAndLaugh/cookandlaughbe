package com.vansisto.cookandlaughbe.helper;

import com.google.common.base.Strings;
import com.vansisto.cookandlaughbe.annotation.Password;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<Password, String> {

    private int min;
    private boolean atLeastOneDigit;
    private boolean atLeastOneLowerCaseLetter;
    private boolean atLeastOneUpperCaseLetter;
    private boolean atLeastOneSpecialCharacter;

    @Override
    public void initialize(Password constraintAnnotation) {
        min = constraintAnnotation.min();
        atLeastOneDigit = constraintAnnotation.atLeastOneDigit();
        atLeastOneLowerCaseLetter = constraintAnnotation.atLeastOneLowerCaseLetter();
        atLeastOneUpperCaseLetter = constraintAnnotation.atLeastOneUpperCaseLetter();
        atLeastOneSpecialCharacter = constraintAnnotation.atLeastOneSpecialCharacter();
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (Strings.isNullOrEmpty(password)) {
            return false;
        }

        StringBuilder patternBuilder = new StringBuilder("^");
        if (atLeastOneDigit) {
            patternBuilder.append("(?=.*[0-9])");
        }
        if (atLeastOneLowerCaseLetter) {
            patternBuilder.append("(?=.*[a-z])");
        }
        if (atLeastOneUpperCaseLetter) {
            patternBuilder.append("(?=.*[A-Z])");
        }
        if (atLeastOneSpecialCharacter) {
            patternBuilder.append("(?=.*[@#$%^&+=])");
        }
        patternBuilder.append(".{").append(min).append(",}$");

        Pattern pattern = Pattern.compile(patternBuilder.toString());
        return pattern.matcher(password).matches();
    }
}
