package ru.spb.avetall.hwarch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(exclude = {RepositoryRestMvcAutoConfiguration.class})
@EnableTransactionManagement
public class HwarchApplication {

    public static void main(String[] args) {
        SpringApplication.run(HwarchApplication.class, args);
    }

}
