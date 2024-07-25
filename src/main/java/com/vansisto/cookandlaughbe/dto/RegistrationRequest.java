package com.vansisto.cookandlaughbe.dto;

import com.vansisto.cookandlaughbe.annotation.Password;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import static com.vansisto.cookandlaughbe.helper.ValidationMessages.EMAIL_SHOULD_BE_VALID;
import static com.vansisto.cookandlaughbe.helper.ValidationMessages.EMAIL_SHOULD_NOT_BE_EMPTY;
import static com.vansisto.cookandlaughbe.helper.ValidationMessages.PASSWORD_NOT_VALID;

@Getter
@Setter
@Accessors(chain = true)
public class RegistrationRequest {

    @NotBlank(message = EMAIL_SHOULD_NOT_BE_EMPTY)
    @Email(message = EMAIL_SHOULD_BE_VALID)
    private String email;

    @Password(min = 6, atLeastOneSpecialCharacter = false, message = PASSWORD_NOT_VALID)
    private String password;
}
