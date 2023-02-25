package com.pme;

import com.pme.exceptions.ErrorModel;
import com.pme.utils.AdditionUtils;
import com.pme.utils.ExtractionUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ErrorModel errorModel () {
        return new ErrorModel();
    }

    @Bean
    public AdditionUtils additionUtils () {
        return new AdditionUtils();
    }

    @Bean
    public ExtractionUtils extractionUtils () {
        return new ExtractionUtils();
    }
}
