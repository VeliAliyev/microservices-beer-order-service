package com.velialiyev.microservicesbeerorderservice.web.mapper;

import com.velialiyev.microservicesbeerorderservice.domain.BeerOrderLine;
import com.velialiyev.microservicesbeerorderservice.web.model.BeerOrderLineDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface BeerOrderLineMapper {
    BeerOrderLineDto beerOrderLineToDto(BeerOrderLine beerOrderLine);
    BeerOrderLine dtoToBeerOrderLine(BeerOrderLineDto beerOrderLineDto);
}
