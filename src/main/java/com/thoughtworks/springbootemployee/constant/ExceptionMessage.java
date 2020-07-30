package com.thoughtworks.springbootemployee.constant;

public enum ExceptionMessage {

    NO_SUCH_DATA("no such data"),
    ILLEGAL_OPERATION("illegal operation");

    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
