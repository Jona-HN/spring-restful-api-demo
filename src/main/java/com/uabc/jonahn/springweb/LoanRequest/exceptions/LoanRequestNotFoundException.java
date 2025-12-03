package com.uabc.jonahn.springweb.LoanRequest.exceptions;

public class LoanRequestNotFoundException extends RuntimeException {

    public LoanRequestNotFoundException(Long id) {
        super("Could not find loan request with id: " + id);
    }

}
