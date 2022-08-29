package com.codeshop.plants.exception;

public class IncompleteUserException extends Exception {

    public IncompleteUserException() {
        super("The provided user data is incomplete (needs firstName, lastName, address)");
    }
}
