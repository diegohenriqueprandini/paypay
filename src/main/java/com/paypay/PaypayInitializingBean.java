package com.paypay;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.TimeZone;

@Log4j2
@Component
public class PaypayInitializingBean implements InitializingBean {

    @Override
    public void afterPropertiesSet() {
        log.info("SYSTEM_TIME_ZONE={}", TimeZone.getDefault().getID());
    }
}
