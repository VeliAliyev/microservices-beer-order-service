package com.velialiyev.microservicesbeerorderservice.repository;

import com.velialiyev.microservicesbeerorderservice.domain.BeerOrder;
import com.velialiyev.microservicesbeerorderservice.domain.BeerOrderLine;
import com.velialiyev.microservicesbeerorderservice.domain.Customer;
import com.velialiyev.microservicesbeerorderservice.domain.OrderStatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.UUID;

public interface BeerOrderRepository extends JpaRepository<BeerOrder, UUID> {

    Page<BeerOrder> findAllByCustomer(Customer customer, Pageable pageable);
    List<BeerOrderLine> findAllByOrderStatus(OrderStatusEnum orderStatusEnum);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    BeerOrder findOneById(UUID id);

}
