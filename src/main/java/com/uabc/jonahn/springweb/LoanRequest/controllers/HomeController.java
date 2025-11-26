package com.uabc.jonahn.springweb.LoanRequest.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/home")
    public String home() {
        return "Welcome to the Loan Request Application!";
    }

}
