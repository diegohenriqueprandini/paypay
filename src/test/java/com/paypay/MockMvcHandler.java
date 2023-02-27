package com.paypay;

import com.fasterxml.jackson.core.type.TypeReference;
import com.paypay.utils.JsonUtils;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultHandler;

import java.nio.charset.Charset;

public class MockMvcHandler<T> implements ResultHandler {

    private final TypeReference<T> valueTypeRef;
    private final Class<T> type;
    private T output;

    public MockMvcHandler(Class<T> type) {
        this.valueTypeRef = null;
        this.type = type;
    }

    public MockMvcHandler(TypeReference<T> valueTypeRef) {
        this.valueTypeRef = valueTypeRef;
        this.type = null;
    }

    @Override
    public void handle(MvcResult result) throws Exception {
        String json = result.getResponse().getContentAsString(Charset.defaultCharset());
        if (valueTypeRef != null)
            output = JsonUtils.fromJson(json, valueTypeRef);
        else
            output = JsonUtils.fromJson(json, type);
    }

    public T get() {
        return output;
    }
}
