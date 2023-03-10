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
    public static final String TASTING_ROOM = "Tasting Room";
    public static final String BEER_1_UPC = "0631234200036";
    public static final String BEER_2_UPC = "0631234300019";
    public static final String BEER_3_UPC = "0083783375213";

    @Override
    public void run(String... args) throws Exception {
        if(customerRepository.count() == 0){
            customerRepository.save(Customer.builder()
                            .customerName(TASTING_ROOM)
                            .apiKey(UUID.randomUUID())
                    .build());
        }
    }
}
