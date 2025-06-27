package com.vansisto.cookandlaughbe.exception.misc;

public final class ExceptionMessages {
    public static final String NOT_FOUND_SUFIX = " '%s' not found";

    public static final String USER_NOT_FOUND_PATTERN = "User with email" + NOT_FOUND_SUFIX;
    public static final String USER_WITH_EMAIL_ALREADY_REGISTERED_PATTERN = "User with email '%s' is already registered";
    public static final String USER_ALREADY_REGISTERED_PATTERN = "User is already registered";

    public static final String ROLE_NOT_FOUND_PATTERN = "Role with name" + NOT_FOUND_SUFIX;

    public static final String ACTIVATION_CODE_NOT_FOUND_PATTERN = "Activation code" + NOT_FOUND_SUFIX;
    public static final String ACTIVATION_CODE_EXPIRED_MESSAGE = "Activation code has expired";

    public static final String RECIPE_NOT_FOUND_PATTERN = "Recipe with id" + NOT_FOUND_SUFIX;

    public static final String S3_ERROR_PATTERN = "An error occurred during storing file '%s' to bucket '%s' with message: '%s'";

    private ExceptionMessages() {
    }
}
