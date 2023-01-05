package com.velialiyev.microservicesbeerorderservice.web.mapper;

import com.velialiyev.microservicesbeerorderservice.domain.BeerOrder;
import com.velialiyev.microservicesbeerorderservice.web.model.BeerOrderDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class, BeerOrderLineMapper.class})
public interface BeerOrderMapper {
    BeerOrderDto beerOrderToDto(BeerOrder beerOrder);
    BeerOrder dtoToBeerOrder(BeerOrderDto beerOrderDto);
}
