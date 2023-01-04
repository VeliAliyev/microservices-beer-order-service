package com.velialiyev.microservicesbeerorderservice.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BeerOrderLine extends BaseEntity{
    @ManyToOne
    private BeerOrder beerOrder;
    private UUID beerId;
    private Integer orderQuantity = 0;
    private Integer quantityAllocated = 0;
}
