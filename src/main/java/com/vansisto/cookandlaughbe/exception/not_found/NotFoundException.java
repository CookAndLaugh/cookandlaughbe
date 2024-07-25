package com.vansisto.cookandlaughbe.exception.not_found;

import com.vansisto.cookandlaughbe.exception.misc.BaseException;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(NOT_FOUND)
public abstract class NotFoundException extends BaseException {
    protected NotFoundException(String message, Object... formatArgs) {
        super(message, formatArgs);
    }
}
