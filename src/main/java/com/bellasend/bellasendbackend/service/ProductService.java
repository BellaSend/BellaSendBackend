package com.bellasend.bellasendbackend.service;

import com.bellasend.bellasendbackend.web.dto.PagedListProductDto;
import com.bellasend.bellasendbackend.web.dto.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto saveSingle(ProductDto productDto);

    PagedListProductDto getAll();

    ProductDto getById(String productId);

    ProductDto fullUpdate(String productId, ProductDto productDto);

    List<ProductDto> saveList(List<ProductDto> productDtos);
}
