package com.vansisto.cookandlaughbe.exception.already_exists;

import static com.vansisto.cookandlaughbe.exception.misc.ExceptionMessages.USER_ALREADY_REGISTERED_PATTERN;
import static com.vansisto.cookandlaughbe.exception.misc.ExceptionMessages.USER_WITH_EMAIL_ALREADY_REGISTERED_PATTERN;

public class UserAlreadyRegisteredException extends AlreadyExistsException {

    public UserAlreadyRegisteredException() {
        super(USER_ALREADY_REGISTERED_PATTERN, "");
    }

    public UserAlreadyRegisteredException(Object... formatArgs) {
        super(USER_WITH_EMAIL_ALREADY_REGISTERED_PATTERN, formatArgs);
    }
}
