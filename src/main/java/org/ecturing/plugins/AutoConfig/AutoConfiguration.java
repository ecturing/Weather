package org.ecturing.plugins.AutoConfig;

import org.ecturing.plugins.Weather;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AutoConfiguration {
    @Bean
    public Weather run(){
        return new Weather();
    }
}
