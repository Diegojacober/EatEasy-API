package com.diegojacober.eateasyapi.rest.exceptions;

public class UserExistsException extends Exception {
    public UserExistsException() {
        super("This email already exists!");
    }
}
