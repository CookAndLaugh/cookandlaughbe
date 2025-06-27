package com.vansisto.cookandlaughbe.exception.misc;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class BaseException extends RuntimeException {
    protected BaseException(String message, Object... formatArgs) {
        super(message.formatted(formatArgs));
        log.error(message.formatted(formatArgs));
    }
}
