package com.uabc.jonahn.springweb.LoanRequest.controllers;

import com.uabc.jonahn.springweb.LoanRequest.assemblers.LoanRequestModelAssembler;
import com.uabc.jonahn.springweb.LoanRequest.exceptions.LoanRequestNotFoundException;
import com.uabc.jonahn.springweb.LoanRequest.models.LoanRequest;
import com.uabc.jonahn.springweb.LoanRequest.repositories.LoanRequestRepository;
import com.uabc.jonahn.springweb.LoanRequest.enums.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> save(@RequestBody LoanRequest request) {
        request.setStatus(Status.IN_PROGRESS);
        EntityModel<LoanRequest> entityModel = assembler.toModel(repository.save(request));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
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
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody LoanRequest newRequest) {
        LoanRequest updatedRequest = repository.findById(id)
                .map(oldRequest -> {
                    oldRequest.setApplicantName(newRequest.getApplicantName());
                    oldRequest.setLoanAmount(newRequest.getLoanAmount());
                    oldRequest.setStatus(newRequest.getStatus());
                    oldRequest.setTerm(newRequest.getTerm());

                    return repository.save(oldRequest);
                })
                .orElseGet(() -> repository.save(newRequest));

        EntityModel<LoanRequest> entityModel = assembler.toModel(updatedRequest);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    // Delete loan request
    @DeleteMapping("/loan_requests/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        // First check if the request exists; else return a 404 code (not found)
        repository.findById(id).orElseThrow(() -> new LoanRequestNotFoundException(id));
        // If it does exist, delete it and return a 204 code (no content)
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
