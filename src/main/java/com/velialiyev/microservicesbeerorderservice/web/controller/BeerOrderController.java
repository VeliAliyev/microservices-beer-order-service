package com.velialiyev.microservicesbeerorderservice.web.controller;

import com.velialiyev.microservicesbeerorderservice.service.BeerOrderService;
import com.velialiyev.microservicesbeerorderservice.web.model.BeerOrderDto;
import com.velialiyev.microservicesbeerorderservice.web.model.BeerOrderPagedList;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customers/{customerId}/")
public class BeerOrderController {

     private final Integer DEFAULT_PAGE_NUMBER = 0;
     private final Integer DEFAULT_PAGE_SIZE = 25;
     private final BeerOrderService beerOrderService;

    @GetMapping("orders")
    public BeerOrderPagedList listOrders(@PathVariable("customerId")UUID customerId,
                                         @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                         @RequestParam(value = "pageSize", required = false) Integer pageSize){
         if(pageNumber == null || pageNumber < 0)
             pageNumber = DEFAULT_PAGE_NUMBER;

         if(pageSize == null || pageSize < 0)
             pageSize = DEFAULT_PAGE_SIZE;

         return beerOrderService.listOrders(customerId, PageRequest.of(pageNumber, pageSize));
     }

    @PostMapping("orders")
    @ResponseStatus(HttpStatus.CREATED)
    public BeerOrderDto placeOrder(@PathVariable("customerId")UUID customerId, @RequestBody BeerOrderDto beerOrderDto){
         return beerOrderService.placeOrder(customerId, beerOrderDto);
     }

    @GetMapping("orders/{orderId}")
    public BeerOrderDto getOrder(@PathVariable("customerId")UUID customerId, @PathVariable("orderId")UUID orderId){
        return beerOrderService.getOrderById(customerId, orderId);
    }

    @PutMapping("orders/{orderId}/pickup")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void pickUpOrder(@PathVariable("customerId")UUID customerId, @PathVariable("orderId")UUID orderId){
        beerOrderService.pickUpOrder(customerId, orderId);
    }
}
