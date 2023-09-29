package com.workintech.twitter.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class AccountException extends RuntimeException{
    private HttpStatus status;
    public AccountException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
