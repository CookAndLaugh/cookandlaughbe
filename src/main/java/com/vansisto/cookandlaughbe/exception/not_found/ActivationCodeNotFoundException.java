package com.vansisto.cookandlaughbe.exception.not_found;

import static com.vansisto.cookandlaughbe.exception.misc.ExceptionMessages.ACTIVATION_CODE_NOT_FOUND_PATTERN;

public class ActivationCodeNotFoundException extends NotFoundException {
    public ActivationCodeNotFoundException(Object... formatArgs) {
        super(ACTIVATION_CODE_NOT_FOUND_PATTERN, formatArgs);
    }
}
