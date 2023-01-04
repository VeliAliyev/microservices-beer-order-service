package com.velialiyev.microservicesbeerorderservice.web.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OrderStatusUpdate extends BaseItem{
    private UUID orderId;
    private String customerRef;
    private String orderStatus;
}
