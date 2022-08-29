package com.codeshop.plants.exception;

public class IncompleteCustomerException extends Exception {

    public IncompleteCustomerException() {
        super("The provided customer data is incomplete (needs firstName, lastName, address)");
    }
}
