package com.lyx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.StringUtils;

@SpringBootApplication
public class SpringAppleApplication {

    private final static Logger logger = LoggerFactory.getLogger(SpringAppleApplication.class);


    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(SpringAppleApplication.class, args);
        String[] names = run.getBeanDefinitionNames();
        for (String name : names) {
            logger.info("name========"+name);
        }
    }

}
