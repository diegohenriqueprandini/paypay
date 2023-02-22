package com.paypay;

import com.paypay.utils.DateUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.stereotype.Component;

import java.util.TimeZone;

@Log4j2
@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        RedisAutoConfiguration.class
})
public class PaypayApplication {

    public static void main(String[] args) {
        DateUtils.setTimeZone();
        initialLogs();
        SpringApplication.run(PaypayApplication.class, args);
    }

    private static void initialLogs() {
        logProperty("user.timezone");
    }

    private static void logProperty(String propertyName) {
        String propertyValue = System.getProperty(propertyName);
        log.info(propertyName + "={}", propertyValue);
    }

    @Log4j2
    @Component
    public static class PaypayInitializingBean implements InitializingBean {
        @Override
        public void afterPropertiesSet() {
            log.info("SYSTEM_TIME_ZONE={}", TimeZone.getDefault().getID());
            log.info("SYSTEM_LOG_LEVEL={}", log.getLevel());
        }
    }
}
