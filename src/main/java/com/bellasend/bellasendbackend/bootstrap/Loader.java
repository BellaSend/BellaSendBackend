package com.bellasend.bellasendbackend.bootstrap;

import com.bellasend.bellasendbackend.domain.Brand;
import com.bellasend.bellasendbackend.domain.Customer;
import com.bellasend.bellasendbackend.domain.Product;
import com.bellasend.bellasendbackend.domain.ProductCategory;
import com.bellasend.bellasendbackend.repository.CustomerRepo;
import com.bellasend.bellasendbackend.repository.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Loader implements CommandLineRunner {
    private final CustomerRepo customerRepo;
    private final ProductRepo productRepo;
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Runner invoked");
       customerLoader();
       productLoader();
        System.out.println("Loader Completed");

    }

    private void productLoader(){
        if(productRepo.count() == 0){
            List<Product> products = List.of(
                    // Product 1
                     Product.builder()
//                             .creationDateTime(Instant.now())
//                             .modificationDateTime(Instant.now())
                            .name("Dumpy Soap")
                            .brand(
                                    Brand.builder()
//                                            .creationDateTime(Instant.now())
//                                            .modificationDateTime(Instant.now())
                                            .name("Generic")
                                            .build()
                            )
                            .productCategory(
                                    ProductCategory.builder()
//                                            .creationDateTime(Instant.now())
//                                            .modificationDateTime(Instant.now())
                                            .name("Toiletries")
                                            .build()
                            )
                            .details("Keeps you clean")
                            .upc("9876543210123")
                            .price(new BigDecimal("2.99"))
                            .build(),

            // Product 2
             Product.builder()
//                     .creationDateTime(Instant.now())
//                     .modificationDateTime(Instant.now())
                    .name("Dumpy Chips")
                    .brand(
                            Brand.builder()
//                                    .creationDateTime(Instant.now())
//                                    .modificationDateTime(Instant.now())
                                    .name("SnackMaster")
                                    .build()
                    )
                    .productCategory(
                            ProductCategory.builder()
//                                    .creationDateTime(Instant.now())
//                                    .modificationDateTime(Instant.now())
                                    .name("Snacks")
                                    .build()
                    )
                    .details("Delicious potato chips")
                    .upc("1234567890123")
                    .price(new BigDecimal("1.49"))
                    .build(),

            // Product 3
           Product.builder()
                    .name("Dumpy Pillow")
//                   .creationDateTime(Instant.now())
//                   .modificationDateTime(Instant.now())
                    .brand(
                            Brand.builder()
//                                    .creationDateTime(Instant.now())
//                                    .modificationDateTime(Instant.now())
                                    .name("ComfortCo")
                                    .build()
                    )
                    .productCategory(
                            ProductCategory.builder()
//                                    .creationDateTime(Instant.now())
//                                    .modificationDateTime(Instant.now())
                                    .name("Home Decor")
                                    .build()
                    )
                    .details("Soft and fluffy pillow")
                    .upc("4567890123456")
                    .price(new BigDecimal("9.99"))
                    .build(),

            // Product 4
        Product.builder()
//                    .creationDateTime(Instant.now())
//                    .modificationDateTime(Instant.now())
                    .name("Dumpy Umbrella")
                    .brand(
                            Brand.builder()
//                                    .creationDateTime(Instant.now())
//                                    .modificationDateTime(Instant.now())
                                    .name("RainShield")
                                    .build()
                    )
                    .productCategory(
                            ProductCategory.builder()
//                                    .creationDateTime(Instant.now())
//                                    .modificationDateTime(Instant.now())
                                    .name("Accessories")
                                    .build()
                    )
                    .details("Keeps you dry on rainy days")
                    .upc("7890123456789")
                    .price(new BigDecimal("5.99"))
                    .build(),

            // Product 5
           Product.builder()
//                   .creationDateTime(Instant.now())
//                   .modificationDateTime(Instant.now())
                    .name("Dumpy Coffee Mug")
                    .brand(
                            Brand.builder()
//                                    .creationDateTime(Instant.now())
//                                    .modificationDateTime(Instant.now())
                                    .name("MugMasters")
                                    .build()
                    )
                    .productCategory(
                            ProductCategory.builder()
//                                    .creationDateTime(Instant.now())
//                                    .modificationDateTime(Instant.now())
                                    .name("Kitchenware")
                                    .build()
                    )
                    .details("Perfect for your morning coffee")
                    .upc("2345678901234")
                    .price(new BigDecimal("3.49"))
                    .build()
            );

            productRepo.saveAll(products).forEach((product)->System.out.println(product.getId()));
            System.out.println(productRepo.count() + " products saved");
        }
    }

    private void customerLoader() {
        if(customerRepo.count() == 0){
            List<Customer> customers = List.of(
                    Customer.builder()
                            .email("example1.com")
                            .password("123")
                            .creationDateTime(Instant.now())
                            .modificationDateTime(Instant.now())
                            .firstName("1example")
                            .lastName("One")
                            .build(),
                    Customer.builder()
                            .email("example2.com")
                            .password("123")
                            .creationDateTime(Instant.now())
                            .modificationDateTime(Instant.now())
                            .firstName("2example")
                            .lastName("One")
                            .build(),
                    Customer.builder()
                            .email("example3.com")
                            .password("123")
                            .creationDateTime(Instant.now())
                            .modificationDateTime(Instant.now())
                            .firstName("e3xample")
                            .lastName("One")
                            .build(),
                    Customer.builder()
                            .email("example4.com")
                            .password("123")
                            .creationDateTime(Instant.now())
                            .modificationDateTime(Instant.now())
                            .firstName("4example")
                            .lastName("One")
                            .build()
            );
            customerRepo.saveAll(customers);
            System.out.println(customerRepo.count() + " customers saved");
        }

//        customerRepo.findAll().forEach(System.out::println);
    }
}
