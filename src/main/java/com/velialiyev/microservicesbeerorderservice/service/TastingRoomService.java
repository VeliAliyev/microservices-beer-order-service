package com.velialiyev.microservicesbeerorderservice.service;

import com.velialiyev.microservicesbeerorderservice.bootstrap.BeerOrderBootstrap;
import com.velialiyev.microservicesbeerorderservice.domain.BeerOrder;
import com.velialiyev.microservicesbeerorderservice.domain.Customer;
import com.velialiyev.microservicesbeerorderservice.repository.BeerOrderRepository;
import com.velialiyev.microservicesbeerorderservice.repository.CustomerRepository;
import com.velialiyev.microservicesbeerorderservice.web.model.BeerOrderDto;
import com.velialiyev.microservicesbeerorderservice.web.model.BeerOrderLineDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class TastingRoomService {
    private final CustomerRepository customerRepository;
    private final BeerOrderService beerOrderService;
    private final BeerOrderRepository beerOrderRepository;
    private final List<String> beerUpcs = new ArrayList<>(
            Arrays.asList(BeerOrderBootstrap.BEER_1_UPC, BeerOrderBootstrap.BEER_2_UPC, BeerOrderBootstrap.BEER_3_UPC ));


    @Transactional
    @Scheduled(fixedRate = 2000)
    public void placeTastingRoomOrder(){

        List<Customer> customerList = customerRepository.findAllByCustomerNameLike(BeerOrderBootstrap.TASTING_ROOM);
        if(customerList.size() == 1){
            doPlaceOrder(customerList.get(0));
        }
        else {
            log.error("Too many or too few tasting room customers found");
        }
    }

    private void doPlaceOrder(Customer customer){

        String beerToOrder = getRandomBeerUpc();

        BeerOrderLineDto beerOrderLineDto = BeerOrderLineDto.builder()
                        .upc(beerToOrder).orderQuantity(new Random().nextInt(6)).build();

        List<BeerOrderLineDto> beerOrderLineDtos = new ArrayList<>();
        beerOrderLineDtos.add(beerOrderLineDto);

        BeerOrderDto beerOrderDto = BeerOrderDto.builder()
                .customerId(customer.getId())
                .customerRef(UUID.randomUUID().toString())
                .beerOrderLines(beerOrderLineDtos)
                .build();

        BeerOrderDto saved = beerOrderService.placeOrder(customer.getId(), beerOrderDto);

    }

    private String getRandomBeerUpc(){
        return beerUpcs.get(new Random().nextInt(beerUpcs.size()));
    }

}
