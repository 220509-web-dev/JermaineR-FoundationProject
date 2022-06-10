package dev.helpDesk.exceptions;

public class UserNameExistsException extends RuntimeException {
    public UserNameExistsException() {
        super("invalid");
    }

    public UserNameExistsException(String message) {
        super(message);
    }

    public UserNameExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNameExistsException(Throwable cause) {
        super(cause);
    }

    public UserNameExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}