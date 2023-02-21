package com.paypay;

import com.paypay.utils.DateUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@Log4j2
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class PaypayApplication {

    public static void main(String[] args) {
        initialLogs();
        DateUtils.setTimeZone();
        SpringApplication.run(PaypayApplication.class, args);
    }

    private static void initialLogs() {
        logProperty("user.timezone");
    }

    private static void logProperty(String property) {
        log.info(property + "={}", System.getProperty(property));
    }
}
