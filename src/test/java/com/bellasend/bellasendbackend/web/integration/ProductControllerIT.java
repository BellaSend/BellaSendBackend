package com.bellasend.bellasendbackend.web.integration;

import com.bellasend.bellasendbackend.domain.Brand;
import com.bellasend.bellasendbackend.domain.Product;
import com.bellasend.bellasendbackend.domain.ProductCategory;
import com.bellasend.bellasendbackend.repository.BrandRepo;
import com.bellasend.bellasendbackend.repository.ProductCategoryRepo;
import com.bellasend.bellasendbackend.repository.ProductRepo;
import com.bellasend.bellasendbackend.service.ProductService;
import com.bellasend.bellasendbackend.web.dto.ProductDto;
import com.bellasend.bellasendbackend.web.mapper.ProductMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
public class ProductControllerIT {
    @Autowired
    WebApplicationContext webApplicationContext;
    @Autowired
    ProductService productService;
    @Autowired
    ProductCategoryRepo productCategoryRepo;
    @Autowired
    BrandRepo brandRepo;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    ProductRepo productRepo;
    @Autowired
    ProductMapper productMapper;

    MockMvc mockMvc;

    private static final String API_PATH = "/api/v1/products";


    @BeforeEach
    public void buildMockMvc(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

//    TODO figure out in mem db for testing
    @Test
    public void saveSingle() throws Exception {
        ProductDto productDto = createProductDto();
        String productJson = objectMapper.writeValueAsString(productDto);
        mockMvc.perform(
                post(API_PATH)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson)
        ).andExpect(status().isCreated())
                .andExpect(mvcResult -> {
                    String content = mvcResult.getResponse().getContentAsString();
                    ProductDto productDtoResponse = objectMapper.readValue(content, ProductDto.class);
                    assertEquals(productDto.getUpc(), productDtoResponse.getUpc());
                });
    }

    @Test
    public void saveList() throws Exception {
        ProductDto productDto1 = createProductDto();
        ProductDto productDto2 = createProductDto();
        String productsJson = objectMapper.writeValueAsString(
                List.of(productDto1, productDto2)
        );
        mockMvc.perform(
                post(API_PATH + "/saveProductList")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productsJson)
        ).andExpect(status().isCreated())
//                .andExpect(mvcResult -> {
//                    String content = mvcResult.getResponse().getContentAsString();
//                    Map<ProductDto> productsDto = objectMapper.readValue(content, Map.class);
//                    assertEquals(productDto1.getUpc(), productsDto.get(0).getUpc());
//                    assertEquals(productDto2.getUpc(), productsDto.get(1).getUpc());
//                })
        ;
    }

    @Test
    public void fullUpdate() throws Exception {
        Product product = productRepo.findAll().get(0);
        product.setName("Name Updated");
        String pId = product.getId();
        product.setId(null);
        product.setVersion(null);
        product.setCreationDateTime(null);
        product.setModificationDateTime(null);
        ProductDto productDto = productMapper.toDto(product);
        String productJson = objectMapper.writeValueAsString(productDto);
        mockMvc.perform(
                put(API_PATH + "/" + pId)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson)
        ).andExpect(status().isOk())
                .andExpect(mvcResult -> {
            String content = mvcResult.getResponse().getContentAsString();
            ProductDto productDtoResponse = objectMapper.readValue(content, ProductDto.class);
            assertEquals(product.getName(), productDtoResponse.getName());
        });
    }

    @Test
    public void getById() throws Exception {
        Product product = productRepo.findAll().get(0);
        mockMvc.perform(
                get(API_PATH + "/" + product.getId())
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
               .andExpect(mvcResult -> {
                    String content = mvcResult.getResponse().getContentAsString();
                    ProductDto productDto = objectMapper.readValue(content, ProductDto.class);
                    assertEquals(product.getId(), productDto.getId());
               });

    }

    @Test
    public void getAllByCategoryBrandPrice() throws Exception {
        Product product = productRepo.findAll().get(0);
        mockMvc.perform(
                        get(API_PATH + "/" + product.getId())
                                .accept(MediaType.APPLICATION_JSON)
                                .param("categoryId", product.getProductCategoryId())
                                .param("brandId", product.getBrandId())
                                .param("price", product.getPrice().toString())
                ).andExpect(status().isOk())
                .andExpect(mvcResult -> {
                    String content = mvcResult.getResponse().getContentAsString();
                    ProductDto productDto = objectMapper.readValue(content, ProductDto.class);
                    assertEquals(product.getId(), productDto.getId());
                });
    }

    private ProductDto createProductDto(){
        List<Brand> brands = brandRepo.findAll();
        List<ProductCategory> categories = productCategoryRepo.findAll();
        return ProductDto.builder()
                .upc(String.valueOf(Math.random() * 10000))
                .name("Milk")
                .price(new BigDecimal("12.0"))
                .brandId(brands.get(0).getId())
                .productCategoryId(categories.get(0).getId())
                .build();
    }

}
