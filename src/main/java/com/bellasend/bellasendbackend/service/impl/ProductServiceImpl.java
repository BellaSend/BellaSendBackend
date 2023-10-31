package com.bellasend.bellasendbackend.service.impl;

import com.bellasend.bellasendbackend.domain.Product;
import com.bellasend.bellasendbackend.exception.NotFoundException;
import com.bellasend.bellasendbackend.repository.ProductRepo;
import com.bellasend.bellasendbackend.service.ProductService;
import com.bellasend.bellasendbackend.web.dto.PagedListProductDto;
import com.bellasend.bellasendbackend.web.dto.ProductDto;
import com.bellasend.bellasendbackend.web.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
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

    @Override
    public PagedListProductDto getAll(
            Integer pageNumber,
            Integer pageSize,
            String categoryName,
            BigDecimal price,
            String brandName
    ) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        Page<Product> productsPage;
        if (!ObjectUtils.isEmpty(categoryName)
                && !ObjectUtils.isEmpty(price)
                && !ObjectUtils.isEmpty(brandName))
        {
            productsPage = productRepo.findProductsByProductCategoryNameAndPriceAndBrandName(
                    categoryName, price, brandName, pageRequest
            );
        } else if (!ObjectUtils.isEmpty(categoryName) &&  !ObjectUtils.isEmpty(price))
        {
            productsPage = productRepo.findProductsByProductCategoryNameAndPrice(
                    categoryName, price, pageRequest
            );
        } else if (!ObjectUtils.isEmpty(categoryName) && !ObjectUtils.isEmpty(brandName))
        {
            productsPage = productRepo.findProductsByProductCategoryNameAndBrandName(
                    categoryName, brandName, pageRequest
            );
        } else if ( !ObjectUtils.isEmpty(price) && !ObjectUtils.isEmpty(brandName)) {
            productsPage = productRepo.findProductsByPriceAndBrandName(
                    price, brandName, pageRequest
            );
        } else if (!ObjectUtils.isEmpty(categoryName)) {
            productsPage = productRepo.findProductsByProductCategoryName(
                    categoryName, pageRequest
            );
        } else if ( !ObjectUtils.isEmpty(price)) {
            productsPage = productRepo.findProductsByPrice(
                    price, pageRequest
            );
        } else if (!ObjectUtils.isEmpty(brandName)) {
            productsPage = productRepo.findProductsByBrandName(
                    brandName, pageRequest
            );
        } else {
            productsPage = productRepo.findAll(pageRequest);
        }

        List<ProductDto> productDtoList = productsPage.stream()
                .map(productMapper::toDto)
                .toList();
        return PagedListProductDto.builder()
                .content(productDtoList)
                .hasNext(productsPage.hasNext())
                .currentPageNumber(productsPage.getNumber())
                .totalPages(productsPage.getTotalPages())
                .totalElements(productsPage.getTotalElements())
                .hasContent(productsPage.hasContent())
                .hasPrevious(productsPage.hasPrevious())
                .pageSize(productsPage.getSize())
                .currentNumOfElements(productsPage.getNumberOfElements())
                .build();

    }
}
