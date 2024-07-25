package com.vansisto.cookandlaughbe.annotation;

import com.vansisto.cookandlaughbe.helper.PasswordValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static com.vansisto.cookandlaughbe.helper.ValidationMessages.PASSWORD_SHOULD_NOT_BE_BLANK;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@NotBlank(message = PASSWORD_SHOULD_NOT_BE_BLANK)
@Constraint(validatedBy = PasswordValidator.class)
@Target({FIELD, PARAMETER})
@Retention(RUNTIME)
public @interface Password {
    String message() default "Invalid password";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int min() default 8;

    boolean atLeastOneDigit() default true;

    boolean atLeastOneLowerCaseLetter() default true;

    boolean atLeastOneUpperCaseLetter() default true;

    boolean atLeastOneSpecialCharacter() default true;
}
