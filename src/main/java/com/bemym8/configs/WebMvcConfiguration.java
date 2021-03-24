package com.bemym8.configs;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Import({ WebSecurityConfiguration.class })
//public class WebMvcConfiguration implements WebMvcConfigurer {
@EnableWebMvc
@ComponentScan
public class WebMvcConfiguration extends WebMvcAutoConfiguration {
    //TODO Here we can add paths to files in our projects
/*
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }
    */
}
