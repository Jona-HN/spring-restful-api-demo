package com.uabc.jonahn.springweb.LoanRequest.assemblers;

import com.uabc.jonahn.springweb.LoanRequest.controllers.LoanRequestController;
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
        return EntityModel.of(request,
                linkTo(methodOn(LoanRequestController.class).findById(request.getId())).withSelfRel(),
                linkTo(methodOn(LoanRequestController.class).findAll()).withRel("loan_requests"));
    }

}
