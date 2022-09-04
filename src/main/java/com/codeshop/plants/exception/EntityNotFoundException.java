package com.codeshop.plants.exception;

public class EntityNotFoundException extends Exception {

    private static final String message = "There is no resource at the specified location";

    public EntityNotFoundException() {
        super(message);
    }
}
