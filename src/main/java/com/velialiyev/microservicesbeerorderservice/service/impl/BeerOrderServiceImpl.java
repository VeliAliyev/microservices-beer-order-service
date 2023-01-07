package com.velialiyev.microservicesbeerorderservice.service.impl;

import com.velialiyev.microservicesbeerorderservice.domain.BeerOrder;
import com.velialiyev.microservicesbeerorderservice.domain.Customer;
import com.velialiyev.microservicesbeerorderservice.domain.OrderStatusEnum;
import com.velialiyev.microservicesbeerorderservice.repository.BeerOrderRepository;
import com.velialiyev.microservicesbeerorderservice.repository.CustomerRepository;
import com.velialiyev.microservicesbeerorderservice.service.BeerOrderService;
import com.velialiyev.microservicesbeerorderservice.web.mapper.BeerOrderMapper;
import com.velialiyev.microservicesbeerorderservice.web.model.BeerOrderDto;
import com.velialiyev.microservicesbeerorderservice.web.model.BeerOrderPagedList;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BeerOrderServiceImpl implements BeerOrderService {

    private final CustomerRepository customerRepository;
    private final BeerOrderRepository beerOrderRepository;
    private final BeerOrderMapper beerOrderMapper;

    @Override
    public BeerOrderPagedList listOrders(UUID customerId, Pageable pageable) {

        Optional<Customer> optionalCustomer = checkCustomer(customerId);
            Page<BeerOrder> beerOrderPage = beerOrderRepository.findAllByCustomer(optionalCustomer.get(), pageable);
            return new BeerOrderPagedList(beerOrderPage.stream().
                    map(beerOrderMapper::beerOrderToDto).collect(Collectors.toList()),
                    PageRequest.of(
                    beerOrderPage.getPageable().getPageNumber(),
                    beerOrderPage.getPageable().getPageSize()
                    ),
                    beerOrderPage.getTotalElements()
            );
    }

    @Override
    public BeerOrderDto placeOrder(UUID customerId, BeerOrderDto beerOrderDto) {

        Optional<Customer> optionalCustomer = checkCustomer(customerId);
        BeerOrder beerOrder = beerOrderMapper.dtoToBeerOrder(beerOrderDto);
        beerOrder.setCustomer(optionalCustomer.get());
        beerOrder.setOrderStatus(OrderStatusEnum.NEW);
        beerOrder.getBeerOrderLines().stream().forEach(beerOrderLine -> beerOrderLine.setBeerOrder(beerOrder));
        BeerOrder saved =  beerOrderRepository.save(beerOrder);
        return beerOrderMapper.beerOrderToDto(saved);
    }

    @Override
    public BeerOrderDto getOrderById(UUID customerId, UUID orderId) {
        checkCustomer(customerId);
        return beerOrderMapper.beerOrderToDto(beerOrderRepository.findOneById(orderId));
    }

    @Override
    public void pickUpOrder(UUID customerId, UUID orderId) {


        BeerOrder beerOrder = beerOrderRepository.findOneById(orderId);

        beerOrder.setOrderStatus(OrderStatusEnum.PICKED_UP);
        beerOrderRepository.save(beerOrder);
    }

    public Optional<Customer> checkCustomer(UUID customerId){
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if(optionalCustomer.isEmpty())
            throw new RuntimeException("Customer not found");
        return optionalCustomer;
    }
}
