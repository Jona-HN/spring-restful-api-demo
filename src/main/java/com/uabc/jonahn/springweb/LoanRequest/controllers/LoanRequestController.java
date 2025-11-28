package com.uabc.jonahn.springweb.LoanRequest.controllers;

import com.uabc.jonahn.springweb.LoanRequest.exceptions.LoanRequestNotFoundException;
import com.uabc.jonahn.springweb.LoanRequest.models.LoanRequest;
import com.uabc.jonahn.springweb.LoanRequest.repositories.LoanRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LoanRequestController {

    private final LoanRequestRepository repository;

    // Get all loan requests
    @GetMapping("/loan_requests")
    List<LoanRequest> findAll() {
        return repository.findAll();
    }

    // Create a loan request
    @PostMapping("/loan_requests")
    LoanRequest save(@RequestBody LoanRequest request) {
        return repository.save(request);
    }

    // Retrieve a loan request by its ID
    @GetMapping("/loan_requests/{id}")
    LoanRequest findById(@PathVariable Long id) {
        return repository.findById(id).
                orElseThrow(() -> new LoanRequestNotFoundException(id));
    }

    // Update loan request information
    @PutMapping("/loan_requests/{id}")
    LoanRequest update(@PathVariable Long id, @RequestBody LoanRequest newRequest) {
        return repository.findById(id)
                .map(oldRequest -> {
                    oldRequest.setApplicantName(newRequest.getApplicantName());
                    oldRequest.setLoanAmount(newRequest.getLoanAmount());
                    oldRequest.setApproved(newRequest.isApproved());
                    oldRequest.setTerm(newRequest.getTerm());

                    return repository.save(oldRequest);
                })
                .orElseGet(() -> repository.save(newRequest));
    }

    // Delete loan request
    @DeleteMapping("/loan_requests/{id}")
    LoanRequest deleteById(@PathVariable Long id) {
        // First check if the request do exists
        // to know which item is being deleted.
        // If a request with such an ID is not found,
        // the findById method will handle the exception.
        LoanRequest loanRequest = findById(id);

        repository.deleteById(id);

        return loanRequest;
    }

}
