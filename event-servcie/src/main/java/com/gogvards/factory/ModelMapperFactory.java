package com.gogvards.factory;

import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import org.modelmapper.ModelMapper;

@Factory
public class ModelMapperFactory {
    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }

}
