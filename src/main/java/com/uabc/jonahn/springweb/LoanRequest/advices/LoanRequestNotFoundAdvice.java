package com.uabc.jonahn.springweb.LoanRequest.advices;

import com.uabc.jonahn.springweb.LoanRequest.exceptions.LoanRequestNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class LoanRequestNotFoundAdvice {

    // Returns HTTP code 404 (not found) everytime
    // LoanRequestNotFoundException is thrown
    @ExceptionHandler(LoanRequestNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String loanRequestNotFoundHandler(LoanRequestNotFoundException ex) {
        return ex.getMessage();
    }

}
