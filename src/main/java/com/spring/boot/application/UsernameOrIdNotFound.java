package com.spring.boot.application;

public class UsernameOrIdNotFound extends Exception {

    private static final long serialVersionUID = -6742033240155746335L;


    public UsernameOrIdNotFound() {
        super();
    }

    public UsernameOrIdNotFound(String message) {
        super(message);
    }
}