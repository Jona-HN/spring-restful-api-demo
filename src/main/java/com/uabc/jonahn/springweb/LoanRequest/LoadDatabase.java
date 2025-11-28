package com.uabc.jonahn.springweb.LoanRequest;

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

            log.info("Preloading {}", repository.save(new LoanRequest("John Doe", 1000F)));
            log.info("Preloading {}", repository.save(new LoanRequest("Jane Doe", 999.9F)));
        };
    }
    
}
