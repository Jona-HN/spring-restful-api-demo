package com.uabc.jonahn.springweb.LoanRequest.controllers;

import com.uabc.jonahn.springweb.LoanRequest.assemblers.LoanRequestModelAssembler;
import com.uabc.jonahn.springweb.LoanRequest.exceptions.LoanRequestNotFoundException;
import com.uabc.jonahn.springweb.LoanRequest.models.LoanRequest;
import com.uabc.jonahn.springweb.LoanRequest.repositories.LoanRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequiredArgsConstructor
public class LoanRequestController {

    private final LoanRequestRepository repository;
    private final LoanRequestModelAssembler assembler;

    // Get all loan requests
    @GetMapping("/loan_requests")
    public CollectionModel<EntityModel<LoanRequest>> findAll() {
        List<EntityModel<LoanRequest>> loanRequests = repository.findAll().stream()
                .map(assembler::toModel)
                .toList();

        return CollectionModel.of(loanRequests, linkTo(methodOn(LoanRequestController.class).findAll()).withSelfRel());
    }

    // Create a loan request
    @PostMapping("/loan_requests")
    public LoanRequest save(@RequestBody LoanRequest request) {
        return repository.save(request);
    }

    // Retrieve a loan request by its ID
    @GetMapping("/loan_requests/{id}")
    public EntityModel<LoanRequest> findById(@PathVariable Long id) {
        LoanRequest request =  repository.findById(id).
                orElseThrow(() -> new LoanRequestNotFoundException(id));

        return assembler.toModel(request);
    }

    // Update loan request information
    @PutMapping("/loan_requests/{id}")
    public LoanRequest update(@PathVariable Long id, @RequestBody LoanRequest newRequest) {
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
    public LoanRequest deleteById(@PathVariable Long id) {
        // First check if the request do exists
        // to know which item is being deleted.
        // If a request with such an ID is not found,
        // the findById method will handle the exception.
        LoanRequest loanRequest = repository.findById(id).
                orElseThrow(() -> new LoanRequestNotFoundException(id));

        repository.deleteById(id);

        return loanRequest;
    }

}
