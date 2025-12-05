package com.uabc.jonahn.springweb.LoanRequest;

import com.uabc.jonahn.springweb.LoanRequest.enums.Status;
import com.uabc.jonahn.springweb.LoanRequest.models.LoanRequest;
import com.uabc.jonahn.springweb.LoanRequest.repositories.LoanRequestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
    
    @Bean
    CommandLineRunner initDatabase(LoanRequestRepository repository) {
        return args -> {
            log.info("Starting database load");

            repository.save(new LoanRequest("John", "Doe", 10000F, Status.IN_PROGRESS));
            repository.save(new LoanRequest("Jane", "Doe", 9999.9F, Status.APPROVED));
            repository.save(new LoanRequest("Jinx", "Doe", 9999.9F, Status.REJECTED));

            repository.findAll().forEach(request -> log.info("Preloaded request: {}", request));

        };
    }
    
}
