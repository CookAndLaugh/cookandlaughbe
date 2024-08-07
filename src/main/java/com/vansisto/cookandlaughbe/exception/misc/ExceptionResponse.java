package com.vansisto.cookandlaughbe.exception.misc;

import java.time.LocalDateTime;

public record ExceptionResponse(
        String path,
        String message,
        int statusCode,
        LocalDateTime dateTime
) {
}
