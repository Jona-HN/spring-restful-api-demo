package com.uabc.jonahn.springweb.LoanRequest.repositories;

import com.uabc.jonahn.springweb.LoanRequest.models.LoanRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRequestRepository extends JpaRepository<LoanRequest, Long> {



}
