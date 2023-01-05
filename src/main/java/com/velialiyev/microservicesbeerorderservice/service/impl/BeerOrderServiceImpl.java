package com.velialiyev.microservicesbeerorderservice.service.impl;

import com.velialiyev.microservicesbeerorderservice.service.BeerOrderService;
import com.velialiyev.microservicesbeerorderservice.web.model.BeerOrderDto;
import com.velialiyev.microservicesbeerorderservice.web.model.BeerOrderPagedList;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public class BeerOrderServiceImpl implements BeerOrderService {
    @Override
    public BeerOrderPagedList listOrders(UUID customerId, Pageable pageable) {
        return null;
    }

    @Override
    public BeerOrderDto placeOrder(UUID customerId, BeerOrderDto beerOrderDto) {
        return null;
    }

    @Override
    public BeerOrderDto getOrderById(UUID customerId, UUID orderId) {
        return null;
    }

    @Override
    public void pickUpOrder(UUID customerId, UUID orderId) {

    }
}
