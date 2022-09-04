package com.codeshop.plants.exception;

public class IncompleteUserException extends RuntimeException {

    private static final String message = """
        The provided user data is incomplete. Expected format is:
        {
            firstName: string,
            lastName: string,
            street: (string),
            city: (string),
            postalCode: (string)
        }
        """;

    public IncompleteUserException() {
        super(message);
    }
}
