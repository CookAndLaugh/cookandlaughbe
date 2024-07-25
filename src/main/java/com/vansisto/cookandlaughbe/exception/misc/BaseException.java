package com.vansisto.cookandlaughbe.exception.misc;

public abstract class BaseException extends RuntimeException {
    protected BaseException(String message, Object... formatArgs) {
        super(message.formatted(formatArgs));
    }
}
