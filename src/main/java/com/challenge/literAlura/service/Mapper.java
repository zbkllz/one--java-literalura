package com.challenge.literAlura.service;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Mapper {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public <T> T getClassFromJson(String json, Class<T> classToMap) {
        try {
            return objectMapper.readValue(json, classToMap);
        } catch (Exception e) {
            throw new RuntimeException("Error converting JSON: " + e.getMessage());
        }
    }
}
