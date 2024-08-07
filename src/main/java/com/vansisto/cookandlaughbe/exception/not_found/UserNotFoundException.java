package com.vansisto.cookandlaughbe.exception.not_found;

import static com.vansisto.cookandlaughbe.exception.misc.ExceptionMessages.USER_NOT_FOUND_PATTERN;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException(Object... formatArgs) {
        super(USER_NOT_FOUND_PATTERN, formatArgs);
    }
}
