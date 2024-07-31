package com.edu.hibernate.exception.user;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String username) {
        super("User " + username + " not found");
    }
}
