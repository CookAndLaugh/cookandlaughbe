package com.vansisto.cookandlaughbe.helper;

public final class ValidationMessages {
    public static final String EMAIL_SHOULD_BE_VALID = "The string has to be a well-formed email address";
    public static final String EMAIL_SHOULD_NOT_BE_EMPTY = "ActivationCodeEmail address cannot be empty";
    public static final String PASSWORD_SHOULD_NOT_BE_BLANK = "Password should not be blank";
    public static final String PASSWORD_NOT_VALID = "The password should be at least 6 characters, should have at least one digit, at least one upper case letter, at least one lower case letter";

    private ValidationMessages() {
    }
}
