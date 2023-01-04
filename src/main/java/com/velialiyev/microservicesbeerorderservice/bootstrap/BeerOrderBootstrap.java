package com.velialiyev.microservicesbeerorderservice.bootstrap;

import com.velialiyev.microservicesbeerorderservice.domain.Customer;
import com.velialiyev.microservicesbeerorderservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class BeerOrderBootstrap implements CommandLineRunner {

    private final CustomerRepository customerRepository;

    @Override
    public void run(String... args) throws Exception {
        if(customerRepository.count() == 0){
            customerRepository.save(Customer.builder()
                            .customerName("Tasting Room")
                            .apiKey(UUID.randomUUID())
                    .build());
        }
    }
}
