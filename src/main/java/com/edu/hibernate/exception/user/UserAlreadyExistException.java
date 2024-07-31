package com.edu.hibernate.exception.user;

public class UserAlreadyExistException extends RuntimeException{

    public UserAlreadyExistException(String username){
        super("User already registered" + username);
    }
}
