package dev.helpDesk.exceptions;

public class CanNotLocateUser extends RuntimeException {
    public CanNotLocateUser() {
        super("Invalid credentials.");
    }

    public CanNotLocateUser(String message) {
        super(message);
    }

    public CanNotLocateUser(String message, Throwable cause) {
        super(message, cause);
    }

    public CanNotLocateUser(Throwable cause) {
        super(cause);
    }

    public CanNotLocateUser(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}