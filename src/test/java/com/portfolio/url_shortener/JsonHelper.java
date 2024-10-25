package com.portfolio.url_shortener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class JsonHelper {

    private JsonHelper() {}

    public static <T> T getResponse(String response, ObjectMapper objectMapper, Class<T> clazz) throws JsonProcessingException {
        return objectMapper.readValue(response, clazz);
    }


}
