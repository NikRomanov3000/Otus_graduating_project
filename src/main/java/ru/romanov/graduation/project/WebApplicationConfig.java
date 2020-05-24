package ru.romanov.graduation.project;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan
@EnableWebMvc
@EntityScan(basePackages="ru.romanov.graduation.project.model")
@EnableJpaRepositories(basePackages="ru.romanov.graduation.project.repository")
public class WebApplicationConfig implements WebMvcConfigurer {

    private final ApplicationContext applicationContext;
    public WebApplicationConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
}
