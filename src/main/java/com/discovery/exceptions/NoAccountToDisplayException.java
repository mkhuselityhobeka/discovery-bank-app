package com.discovery.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No Account To display for this id")
public class NoAccountToDisplayException extends RuntimeException{
    private static final long serialVersionUID = 1L;


}
