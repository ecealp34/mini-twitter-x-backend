package com.workintech.twitter.exceptions;

import com.workintech.twitter.entity.Tweet;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<AccountResponse> handleException(AccountException accountException) {
        AccountResponse response = new AccountResponse(accountException.getStatus().value(),
               accountException.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, accountException.getStatus());
    }

    @ExceptionHandler
    public ResponseEntity<TweetResponse> handleException(TweetException tweetException) {
        TweetResponse response = new TweetResponse(tweetException.getStatus().value(),
                tweetException.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, tweetException.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleBindErrors(MethodArgumentNotValidException exception) {
        List errorList = exception.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> {
                    Map<String, String> errors = new HashMap<>();
                    errors.put(fieldError.getField(), fieldError.getDefaultMessage());
                    return errors;
                }).collect(Collectors.toList());
        return ResponseEntity.badRequest().body(errorList);
    }

    @ExceptionHandler
    public ResponseEntity<AccountResponse> handleException(Exception exception) {
        AccountResponse response = new AccountResponse(HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

}
