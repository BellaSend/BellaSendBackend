package com.bellasend.bellasendbackend.service;

import com.bellasend.bellasendbackend.web.dto.PagedListProductDto;
import com.bellasend.bellasendbackend.web.dto.ProductDto;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    ProductDto saveSingle(ProductDto productDto);


    ProductDto getById(String productId);

    ProductDto fullUpdate(String productId, ProductDto productDto);

    List<ProductDto> saveList(List<ProductDto> productDtos);

    PagedListProductDto getAll(Integer pageNumber, Integer pageSize, String categoryId, BigDecimal price, String brandId);
}
