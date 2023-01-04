package com.velialiyev.microservicesbeerorderservice.web.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BeerOrderLineDto extends BaseItem{
    private String upc;
    private String beerName;
    private String beerId;
    private Integer orderQuantity = 0;
}
