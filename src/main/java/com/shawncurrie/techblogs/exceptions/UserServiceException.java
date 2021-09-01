package com.shawncurrie.techblogs.exceptions;

public class UserServiceException extends RuntimeException {

    private static final long serialVersionUID = 4327894732894792L;

    public UserServiceException(String message) {
        super(message);
    }
}
