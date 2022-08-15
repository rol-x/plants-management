package com.codeshop.project.exception;

public class EntityNotFoundException extends Exception {

    public EntityNotFoundException() {
        super("There is no resource at the specified location");
    }
}
