package com.kodilla.carrentalbackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableScheduling
@EnableSwagger2
@Configuration
public class CoreConfiguration implements WebMvcConfigurer {
}
