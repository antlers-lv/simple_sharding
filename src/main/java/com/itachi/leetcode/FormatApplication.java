package com.itachi.leetcode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@EnableConfigurationProperties
public class FormatApplication extends SpringBootServletInitializer{

   @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(FormatApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(FormatApplication.class, args);
    }


}
