package com.vansisto.cookandlaughbe.exception.already_exists;

import com.vansisto.cookandlaughbe.exception.misc.BaseException;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.CONFLICT;

@ResponseStatus(CONFLICT)
public abstract class AlreadyExistsException extends BaseException {
    protected AlreadyExistsException(String message, Object... formatArgs) {
        super(message, formatArgs);
    }
}
