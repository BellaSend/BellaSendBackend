package com.bellasend.bellasendbackend.bootstrap;

import com.bellasend.bellasendbackend.domain.Customer;
import com.bellasend.bellasendbackend.repository.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Loader implements CommandLineRunner {
    private final CustomerRepo customerRepo;
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Runner invoked");
       customerLoader();
    }

    private void customerLoader() {
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
//        customerRepo.saveAll(customers);
        customerRepo.findAll().forEach(System.out::println);
    }
}
