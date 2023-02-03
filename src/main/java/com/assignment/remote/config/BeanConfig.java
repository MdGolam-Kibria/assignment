package com.assignment.remote.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Bean
    public ModelMapper getModelMapper() {//For model mapper
        return new ModelMapper();
    }
    @Bean("prettyGson")
    public Gson getGson() {
        return new Gson().newBuilder().setPrettyPrinting().create();
    }

    @Bean
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }
}
