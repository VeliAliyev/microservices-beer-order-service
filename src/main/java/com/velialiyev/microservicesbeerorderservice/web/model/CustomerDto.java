package com.velialiyev.microservicesbeerorderservice.web.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CustomerDto extends BaseItem {
    private String name;
}

