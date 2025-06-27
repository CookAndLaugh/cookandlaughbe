package com.vansisto.cookandlaughbe.exception;

import com.vansisto.cookandlaughbe.exception.misc.BaseException;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.vansisto.cookandlaughbe.exception.misc.ExceptionMessages.S3_ERROR_PATTERN;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ResponseStatus(INTERNAL_SERVER_ERROR)
public class S3StoringException extends BaseException {
    public S3StoringException(Object ...formatArgs) {
        super(S3_ERROR_PATTERN, formatArgs);
    }
}
