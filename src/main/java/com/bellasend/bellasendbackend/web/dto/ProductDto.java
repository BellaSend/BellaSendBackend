package com.bellasend.bellasendbackend.web.dto;

import com.bellasend.bellasendbackend.domain.Brand;
import com.bellasend.bellasendbackend.domain.ProductCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.List;

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
    private ProductCategory productCategory;
    @NotBlank
    @NotNull
    private String details;
    @NotNull
    private Brand brand;
}
