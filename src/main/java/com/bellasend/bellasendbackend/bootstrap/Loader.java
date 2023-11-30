package com.bellasend.bellasendbackend.bootstrap;

import com.bellasend.bellasendbackend.domain.Brand;
import com.bellasend.bellasendbackend.domain.Product;
import com.bellasend.bellasendbackend.domain.ProductCategory;
import com.bellasend.bellasendbackend.repository.*;
import com.bellasend.bellasendbackend.web.dto.ProductPreviewDto;
import com.bellasend.bellasendbackend.web.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Loader implements CommandLineRunner {
    private final CustomerRepo customerRepo;
    private final ProductRepo productRepo;
    private final TestRepo testRepo;
    private final ProductCategoryRepo productCategoryRepo;
    private final BrandRepo brandRepo;
    private final ProductMapper productMapper;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Runner invoked");
//       customerLoader();
        brandLoader();
        productCategoryLoader();
       productLoader();



    }

    private void brandLoader(){
        List<Brand> brands = List.of(
            Brand.builder()
                .name("Generic")
                .build(),
            Brand.builder()
                .name("SnackMaster")
                .build(),
            Brand.builder()
                .name("ComfortCo")
                .build(),
            Brand.builder()
                .name("RainShield")
                .build(),
            Brand.builder()
                .name("MugMasters")
                .build()
        );
        brandRepo.saveAll(brands);
    }

    private void productCategoryLoader(){
        List<ProductCategory> productCategories = List.of(
                ProductCategory.builder()
                        .name("Toiletries")
                        .build(),
                ProductCategory.builder()
                        .name("Snacks")
                        .build(),
                ProductCategory.builder()
                        .name("Home Decor")
                        .build(),
                ProductCategory.builder()
                        .name("Accessories")
                        .build(),
                ProductCategory.builder()
                        .name("Kitchenware")
                        .build()
        );
        productCategoryRepo.saveAll(productCategories);
    }

    private void productLoader(){
        if(productRepo.count() == 0){
            List<Brand> brands = brandRepo.findAll();
            List<ProductCategory> productCategories = productCategoryRepo.findAll();
            List<Product> products = List.of(
                    // Product 1
                     Product.builder()
                            .name("Dumpy Soap")
                             .brandId(brands.get(0).getId())
                             .productCategoryId(productCategories.get(0).getId())
                            .details("Keeps you clean")
                            .upc("9876543210123")
                            .price(new BigDecimal("2.99"))
                            .build(),

            // Product 2
             Product.builder()
                    .name("Dumpy Chips")
                     .brandId(brands.get(1).getId())
                     .productCategoryId(productCategories.get(1).getId())
                    .details("Delicious potato chips")
                    .upc("1234567890123")
                    .price(new BigDecimal("1.49"))
                    .build(),

            // Product 3
           Product.builder()
                    .name("Dumpy Pillow")
                    .brandId(brands.get(2).getId())
                   .productCategoryId(productCategories.get(2).getId())
                    .details("Soft and fluffy pillow")
                    .upc("4567890123456")
                    .price(new BigDecimal("9.99"))
                    .build(),

            // Product 4
        Product.builder()
                    .name("Dumpy Umbrella")
                    .brandId(brands.get(3).getId())
                    .productCategoryId(productCategories.get(3).getId())
                    .details("Keeps you dry on rainy days")
                    .upc("7890123456789")
                    .price(new BigDecimal("5.99"))
                    .build(),

            // Product 5
           Product.builder()
                    .name("Dumpy Coffee Mug")
                    .brandId(brands.get(4).getId())
                   .productCategoryId(productCategories.get(4).getId())
                    .details("Perfect for your morning coffee")
                    .upc("2345678901234")
                    .price(new BigDecimal("3.49"))
                    .build()
            );

            //bi directionality
            List<ProductPreviewDto> savedProductsPreview = productRepo.saveAll(products).stream()
                    .map(productMapper::toPreview)
                    .toList();
            brands.forEach(brand -> brand.setProducts(savedProductsPreview));
            brandRepo.saveAll(brands);

            productCategories.forEach(cateory -> cateory.setProducts(savedProductsPreview));
            productCategoryRepo.saveAll(productCategories);
            System.out.println(productRepo.count() + " products saved");
        }
    }

//    private void customerLoader() {
//        if(customerRepo.count() == 0){
//            List<Customer> customers = List.of(
//                    Customer.builder()
//                            .email("example1.com")
//                            .password("123")
//                            .creationDateTime(Instant.now())
//                            .modificationDateTime(Instant.now())
//                            .firstName("1example")
//                            .lastName("One")
//                            .build(),
//                    Customer.builder()
//                            .email("example2.com")
//                            .password("123")
//                            .creationDateTime(Instant.now())
//                            .modificationDateTime(Instant.now())
//                            .firstName("2example")
//                            .lastName("One")
//                            .build(),
//                    Customer.builder()
//                            .email("example3.com")
//                            .password("123")
//                            .creationDateTime(Instant.now())
//                            .modificationDateTime(Instant.now())
//                            .firstName("e3xample")
//                            .lastName("One")
//                            .build(),
//                    Customer.builder()
//                            .email("example4.com")
//                            .password("123")
//                            .creationDateTime(Instant.now())
//                            .modificationDateTime(Instant.now())
//                            .firstName("4example")
//                            .lastName("One")
//                            .build()
//            );
//            customerRepo.saveAll(customers);
//            System.out.println(customerRepo.count() + " customers saved");
//        }

//        customerRepo.findAll().forEach(System.out::println);
//    }
}
