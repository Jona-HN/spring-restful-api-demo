package com.uabc.jonahn.springweb.LoanRequest.assemblers;

import com.uabc.jonahn.springweb.LoanRequest.controllers.LoanRequestController;
import com.uabc.jonahn.springweb.LoanRequest.enums.Status;
import com.uabc.jonahn.springweb.LoanRequest.models.LoanRequest;
import lombok.NonNull;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class LoanRequestModelAssembler implements RepresentationModelAssembler<LoanRequest, @NonNull EntityModel<LoanRequest>> {

    @Override
    public EntityModel<LoanRequest> toModel(@NonNull LoanRequest request) {
        // Unconditional links to single-item resource and aggregate root
        EntityModel<LoanRequest> entityModel = EntityModel.of(request,
                linkTo(methodOn(LoanRequestController.class).findById(request.getId())).withSelfRel(),
                linkTo(methodOn(LoanRequestController.class).findAll()).withRel("loan_requests"));

        // Conditional links based on state of the loan request
        if (request.getStatus() == Status.IN_PROGRESS) {
            entityModel.add(linkTo(methodOn(LoanRequestController.class).reject(request.getId())).withRel("reject"));
            entityModel.add(linkTo(methodOn(LoanRequestController.class).approve(request.getId())).withRel("approve"));
        }

        return entityModel;
    }

}
