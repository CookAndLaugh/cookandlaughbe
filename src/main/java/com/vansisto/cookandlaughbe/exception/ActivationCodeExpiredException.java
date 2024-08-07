package com.vansisto.cookandlaughbe.exception;

import com.vansisto.cookandlaughbe.exception.misc.BaseException;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.vansisto.cookandlaughbe.exception.misc.ExceptionMessages.ACTIVATION_CODE_EXPIRED_MESSAGE;
import static org.springframework.http.HttpStatus.GONE;

@ResponseStatus(GONE)
public class ActivationCodeExpiredException extends BaseException {
    public ActivationCodeExpiredException() {
        super(ACTIVATION_CODE_EXPIRED_MESSAGE, new Object());
    }
}
