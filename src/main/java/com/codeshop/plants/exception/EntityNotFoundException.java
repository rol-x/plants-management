package com.codeshop.plants.exception;

public class EntityNotFoundException extends Exception {

    public EntityNotFoundException() {
        super("There is no resource at the specified location");
    }
}
