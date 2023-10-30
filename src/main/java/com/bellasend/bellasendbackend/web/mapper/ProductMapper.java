package com.bellasend.bellasendbackend.web.mapper;

import com.bellasend.bellasendbackend.domain.Product;
import com.bellasend.bellasendbackend.web.dto.ProductDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface ProductMapper {
    ProductDto toDto(Product product);
    Product toProduct(ProductDto productDto);
}
