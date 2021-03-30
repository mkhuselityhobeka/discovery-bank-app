package com.discovery.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Atm not registered")
public class AtmNotRegisteredException extends RuntimeException{

    public AtmNotRegisteredException (String message){
        super();
    }
}
