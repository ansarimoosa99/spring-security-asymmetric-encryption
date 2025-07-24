package com.ansari.app.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
public enum ErrorCode {

    USER_NOT_FOUND("USER_NOT_FOUND", "User not found with id %s", NOT_FOUND),
    CHANGE_PASSWORD_MISMATCH("CHANGE_PASSWORD_MISMATCH", "Current password does not match old password", BAD_REQUEST),
    INVALID_CURRENT_PASSWORD("INVALID_CURRENT_PASSWORD", "Current password is invalid", BAD_REQUEST),
    ACCCOUNT_ALREADY_DEACTIVATED("ACCCOUNT_ALREADY_DEACTIVATED", "Account is already deactivated", BAD_REQUEST),
    ACCCOUNT_ALREADY_ACTIVATED("ACCCOUNT_ALREADY_ACTIVATED", "Account is already activated", BAD_REQUEST),
    ACCCOUNT_ALREADY_DELETED("ACCCOUNT_ALREADY_DELETED", "Account is already deleted", BAD_REQUEST),
    EMAIL_ALREADY_EXISTS("EMAIL_ALREADY_EXISTS", "Email already exists", BAD_REQUEST),
    PHONE_ALREADY_EXISTS("PHONE_ALREADY_EXISTS", "Phone Number already exists", BAD_REQUEST),
    PASSWORD_MISMATCH("PASSWORD_MISMATCH", "Passwords do not mismatch", BAD_REQUEST);

    private final String code;
    private final String defaultMessage;
    private final HttpStatus httpStatus;

    //localization and internationalization
    // l10n    i18n

    ErrorCode(final String code, final String defaultMessage, final HttpStatus httpStatus) {
        this.code = code;
        this.defaultMessage = defaultMessage;
        this.httpStatus = httpStatus;
    }
}
