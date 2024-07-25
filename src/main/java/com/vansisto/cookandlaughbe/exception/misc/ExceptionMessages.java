package com.vansisto.cookandlaughbe.exception.misc;

public final class ExceptionMessages {
    public static final String USER_NOT_FOUND_PATTERN = "User with email '%s' not found";
    public static final String USER_WITH_EMAIL_ALREADY_REGISTERED_PATTERN = "User with email '%s' is already registered";
    public static final String USER_ALREADY_REGISTERED_PATTERN = "User is already registered";

    public static final String ROLE_NOT_FOUND_PATTERN = "Role with name '%s' not found";

    public static final String ACTIVATION_CODE_NOT_FOUND_PATTERN = "Activation code '%s' not found";
    public static final String ACTIVATION_CODE_EXPIRED_MESSAGE = "Activation code has expired";

    private ExceptionMessages() {
    }
}
