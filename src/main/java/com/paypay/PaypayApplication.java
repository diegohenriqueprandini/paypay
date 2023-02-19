package com.paypay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class PaypayApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaypayApplication.class, args);
    }

}
