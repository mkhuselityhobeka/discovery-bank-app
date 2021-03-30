package com.discovery.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.FORBIDDEN, reason="Insufficient funds")

public class InSufficientFundsException extends RuntimeException{

    public InSufficientFundsException(String message){

    }
}
