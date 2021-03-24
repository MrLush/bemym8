package com.bemym8.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Import({ WebSecurityConfiguration.class })
public class WebMvcConfiguration implements WebMvcConfigurer {
    //TODO Here we can add paths to files in our projects

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }

    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
            registry
                    .addResourceHandler("/resources/**")
                    .addResourceLocations("classpath:/resources/static/");
    }

}
