package com.bellasend.bellasendbackend.web.controller;

import com.bellasend.bellasendbackend.service.ProductService;
import com.bellasend.bellasendbackend.web.dto.PagedListProductDto;
import com.bellasend.bellasendbackend.web.dto.ProductDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Validated //method args validation
public class ProductController {
    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 6;

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDto> saveSingle(
        @NotNull @Valid @RequestBody ProductDto productDto
    ){
        return new ResponseEntity<>( productService.saveSingle(productDto), HttpStatus.CREATED);
    }
    @PostMapping("/saveProductList")
    public ResponseEntity<List<ProductDto>> saveList(
        @NotNull  @Valid  @RequestBody List<ProductDto> productDtos
    ){
        return new ResponseEntity<>(productService.saveList(productDtos), HttpStatus.CREATED);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductDto> fullUpdate(
           @NotBlank @NotNull @PathVariable(name = "productId") String productId,
          @NotNull @Valid @RequestBody ProductDto productDto
    ){
        return new ResponseEntity<>(productService.fullUpdate(productId, productDto), HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getById(
          @NotBlank @NotNull @PathVariable(name = "productId") String productId
    ){
        return new ResponseEntity<>(productService.getById(productId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PagedListProductDto> getAll(
            @RequestParam(name = "pageNumber", required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", required = false) Integer pageSize,
            @RequestParam(name = "categoryId", required = false) String categoryId,
            @RequestParam(name = "price", required = false)BigDecimal price,
            @RequestParam(name = "brandId", required = false) String brandId
            ){
        pageNumber = pageNumber == null || pageNumber < 0 ? DEFAULT_PAGE_NUMBER : pageNumber;
        pageSize = pageSize == null || pageSize < 0 ? DEFAULT_PAGE_SIZE : pageSize;
        return new ResponseEntity<>(productService.getAll(
                pageNumber, pageSize, categoryId, price, brandId
        ), HttpStatus.OK);
    }

}
