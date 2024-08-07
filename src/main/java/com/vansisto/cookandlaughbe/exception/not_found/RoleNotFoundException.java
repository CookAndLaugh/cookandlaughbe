package com.vansisto.cookandlaughbe.exception.not_found;

import static com.vansisto.cookandlaughbe.exception.misc.ExceptionMessages.ROLE_NOT_FOUND_PATTERN;

public class RoleNotFoundException extends NotFoundException {
    public RoleNotFoundException(Object... formatArgs) {
        super(ROLE_NOT_FOUND_PATTERN, formatArgs);
    }
}
