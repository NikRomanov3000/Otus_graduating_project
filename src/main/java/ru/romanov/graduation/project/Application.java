package ru.romanov.graduation.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({
        "ru.romanov.graduation.project.repository",
        "ru.romanov.graduation.project.service",
        "ru.romanov.graduation.project.controller"
})
@EntityScan(basePackages="ru.romanov.graduation.project.model")
@EnableJpaRepositories(basePackages="ru.romanov.graduation.project.repository")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
