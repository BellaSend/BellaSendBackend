package com.bellasend.bellasendbackend.service.impl;

import com.bellasend.bellasendbackend.domain.Product;
import com.bellasend.bellasendbackend.exception.NotFoundException;
import com.bellasend.bellasendbackend.repository.ProductRepo;
import com.bellasend.bellasendbackend.service.ProductService;
import com.bellasend.bellasendbackend.web.dto.PagedListProductDto;
import com.bellasend.bellasendbackend.web.dto.ProductDto;
import com.bellasend.bellasendbackend.web.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductMapper productMapper;
    private final ProductRepo productRepo;

    @Override
    public ProductDto saveSingle(ProductDto productDto) {
        return productMapper.toDto(
                productRepo.save(productMapper.toProduct(productDto))
        );
    }

    @Override
    public PagedListProductDto getAll() {
//TODO write impl
        return null;
    }

    @Override
    public ProductDto getById(String productId) {
        Product product = productRepo.findById(productId)
                .orElseThrow(()-> new NotFoundException("No product with such ID"));
        return productMapper.toDto(product);
    }

    @Override
    public ProductDto fullUpdate(String productId, ProductDto productDto) {
        Product productInDB = productRepo.findById(productId)
                .orElseThrow(()-> new NotFoundException("No product with such ID"));

        productInDB.setUpc(productDto.getUpc());
        productInDB.setName(productDto.getName());
        productInDB.setProductCategory(productDto.getProductCategory());
        productInDB.setPrice(productDto.getPrice());
        productInDB.setDetails(productDto.getDetails());
        productInDB.setBrand(productDto.getBrand());

        productInDB.setModificationDateTime(Instant.now());
        return productMapper.toDto(productRepo.save(productInDB));
    }

    @Override
    public List<ProductDto> saveList(List<ProductDto> productDtos) {
        List<Product> products = productDtos.stream()
                .map(productMapper::toProduct).toList();
        return productRepo.saveAll(products).stream()
                .map(productMapper::toDto)
                .toList();
    }
}
