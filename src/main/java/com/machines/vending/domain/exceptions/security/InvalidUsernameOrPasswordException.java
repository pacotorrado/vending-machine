package com.machines.vending.domain.exceptions.security;

public class InvalidUsernameOrPasswordException extends Exception {
    public InvalidUsernameOrPasswordException() {
        super("Invalid username or password. Try again!");
    }
}
