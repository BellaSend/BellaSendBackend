package com.bellasend.bellasendbackend.domain;

import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Product extends BaseEntity{

    private String name;

    @Indexed(unique = true)
    private String upc;

    private BigDecimal price;

    private String productCategoryId;

    private String details;

    private String brandId;
}
