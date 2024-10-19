package com.aj.game.utilities;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;

public class JsonReader {
    private final ObjectMapper objectMapper;
    private static JsonReader instance;

    private JsonReader() {
        this.objectMapper = new ObjectMapper();
    }

    public static JsonReader getInstance() {
        if (instance == null) {
            instance = new JsonReader();
        }
        return instance;
    }

    public <T> T readJson(String path, Class<T> clazz) throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(path);

        if (inputStream == null) {
            throw new IllegalArgumentException("File not found: " + path);
        }

        return objectMapper.readValue(inputStream, clazz);
    }
}
