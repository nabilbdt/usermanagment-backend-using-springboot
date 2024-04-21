package com.user.backendspringbootapp.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Long id){
        super("could not found user with the id "+id);
    }
}
