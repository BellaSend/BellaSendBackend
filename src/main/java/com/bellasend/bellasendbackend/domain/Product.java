package com.bellasend.bellasendbackend.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document
@SuperBuilder
@NoArgsConstructor
@Data
public class Product extends BaseEntity{
    private String name;
//TODO enforce unquie upc
    private String upc;
    private BigDecimal price;
    private ProductCategory productCategory;
    private String details;
    private Brand brand;
}
