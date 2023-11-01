package com.bellasend.bellasendbackend.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document
@SuperBuilder
@NoArgsConstructor
@Setter
@Getter
public class Product extends BaseEntity{

    private String name;

    @Indexed(unique = true)
    private String upc;

    private BigDecimal price;

    private ProductCategory productCategory;

    private String details;

    private Brand brand;
}
