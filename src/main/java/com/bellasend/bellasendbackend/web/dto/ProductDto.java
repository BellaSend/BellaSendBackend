package com.bellasend.bellasendbackend.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto extends BaseDto{
    @NotBlank
    @NotNull
    private String name;
    @NotBlank
    @NotNull
    private String upc;
    @NotNull
    @Positive
    private BigDecimal price;
    @NotNull
    private  String productCategoryId;

    private String details;
    @NotNull
    private String brandId;
}
