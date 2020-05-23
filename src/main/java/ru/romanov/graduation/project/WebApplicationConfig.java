package ru.romanov.graduation.project;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan
@EnableWebMvc
public class WebApplicationConfig implements WebMvcConfigurer {

    private final ApplicationContext applicationContext;
    public WebApplicationConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
}
