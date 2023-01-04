package com.velialiyev.microservicesbeerorderservice.service;

import com.velialiyev.microservicesbeerorderservice.web.model.BeerOrderDto;
import com.velialiyev.microservicesbeerorderservice.web.model.BeerOrderPagedList;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface BeerOrderService {
    BeerOrderPagedList listOrders(UUID customerId, Pageable pageable);

    BeerOrderDto placeOrder(UUID customerId, BeerOrderDto beerOrderDto);

    BeerOrderDto getOrderById(UUID customerId, UUID orderId);

    void pickUpOrder(UUID customerId, UUID orderId);
}
